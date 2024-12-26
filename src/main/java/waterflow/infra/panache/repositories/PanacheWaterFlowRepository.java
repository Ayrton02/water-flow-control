package waterflow.infra.panache.repositories;

import core.valueobjects.ID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import user.infra.panache.entities.PanacheUserEntity;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionRepository;
import waterflow.domain.entities.WaterContainer;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.entities.WaterPump;
import waterflow.domain.entities.WaterSource;
import waterflow.infra.panache.entities.PanacheWaterFlowSessionEntity;

@ApplicationScoped
public class PanacheWaterFlowRepository implements CreateWaterFlowSessionRepository {
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
    user.setId(session.getUserId().getValue());
    return new PanacheWaterFlowSessionEntity(
        session.getId().getValue(),
        session.getStartedAt().toLocalDateTime(),
        session.getFinishedAt().toLocalDateTime(),
        session.getStatus(),
        user,
        session.getCreatedAt().toLocalDateTime(),
        session.getUpdatedAt().toLocalDateTime()
    );
  }
}
