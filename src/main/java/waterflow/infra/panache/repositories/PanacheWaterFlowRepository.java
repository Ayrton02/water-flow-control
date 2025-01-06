package waterflow.infra.panache.repositories;

import core.valueobjects.DateTime;
import core.valueobjects.ID;
import core.valueobjects.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import user.infra.panache.entities.PanacheUserEntity;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionRepository;
import waterflow.application.usecases.startwaterflowsession.StartWaterFlowSessionRepository;
import waterflow.domain.entities.WaterContainer;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.entities.WaterPump;
import waterflow.domain.entities.WaterSource;
import waterflow.infra.panache.entities.PanacheWaterContainerEntity;
import waterflow.infra.panache.entities.PanacheWaterFlowSessionEntity;
import waterflow.infra.panache.entities.PanacheWaterPumpEntity;
import waterflow.infra.panache.entities.PanacheWaterSourceEntity;

import java.util.Optional;

@ApplicationScoped
public class PanacheWaterFlowRepository implements CreateWaterFlowSessionRepository, StartWaterFlowSessionRepository {
  private final PanacheWaterSourceRepository sourceRepository;
  private final PanacheWaterContainerRepository containerRepository;
  private final PanacheWaterPumpRepository pumpRepository;

  @Inject
  PanacheWaterFlowRepository(
      PanacheWaterSourceRepository sourceRepository,
      PanacheWaterPumpRepository pumpRepository,
      PanacheWaterContainerRepository containerRepository
  ) {
    this.sourceRepository = sourceRepository;
    this.pumpRepository = pumpRepository;
    this.containerRepository = containerRepository;
  }

  @Override
  public WaterPump findPumpById(ID id) {
    return this.pumpRepository.findPumpById(id);
  }

  @Override
  public WaterFlowSession findById(ID id) {
    Optional<PanacheWaterFlowSessionEntity> entity = PanacheWaterFlowSessionEntity.find("id", id.getValue()).firstResultOptional();
    return entity.map(this::toModel).orElse(null);
  }

  @Override
  @Transactional
  public void save(WaterFlowSession session) {
    PanacheWaterFlowSessionEntity.persist(this.toEntity(session));
  }

  @Override
  public WaterSource findSourceById(ID id) {
    return this.sourceRepository.findSourceById(id);
  }

  @Override
  @Transactional
  public WaterContainer findContainerById(ID id) {
    return this.containerRepository.findContainerById(id);
  }

  private PanacheWaterFlowSessionEntity toEntity(WaterFlowSession session) {
    PanacheUserEntity user = new PanacheUserEntity();
    PanacheWaterSourceEntity source = new PanacheWaterSourceEntity();
    PanacheWaterContainerEntity container = new PanacheWaterContainerEntity();
    PanacheWaterPumpEntity pump = new PanacheWaterPumpEntity();

    user.setId(session.getUserId().getValue());
    source.setId(session.getWaterSource().getId().getValue());
    container.setId(session.getWaterContainer().getId().getValue());
    pump.setId(session.getWaterPump().getId().getValue());

    return new PanacheWaterFlowSessionEntity(
        session.getId().getValue(),
        session.getStartedAt().toLocalDateTime(),
        session.getFinishedAt().toLocalDateTime(),
        session.getStatus(),
        user,
        source,
        container,
        pump,
        session.getCreatedAt().toLocalDateTime(),
        session.getUpdatedAt().toLocalDateTime()
    );
  }

  private WaterFlowSession toModel(PanacheWaterFlowSessionEntity entity) {
    WaterFlowSession session = WaterFlowSession.create(
        this.sourceRepository.toModel(entity.getSource()),
        this.containerRepository.toModel(entity.getContainer()),
        this.pumpRepository.toModel(entity.getPump()),
        UUID.from(entity.getUser().getId())
    );

    session.setCreatedAt(DateTime.parse(entity.getCreatedAt().toString()));
    session.setUpdatedAt(DateTime.parse(session.getUpdatedAt().toString()));
    return session;
  }
}
