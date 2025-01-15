package waterflow.infra.panache.repositories;

import core.valueobjects.DateTime;
import core.valueobjects.ID;
import core.valueobjects.UUID;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import user.domain.exceptions.UserNotFoundException;
import user.infra.panache.entities.PanacheUserEntity;
import waterflow.application.usecases.completewaterflowsession.CompleteWaterFlowSessionRepository;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionRepository;
import waterflow.application.usecases.findwaterflowsession.FindWaterFlowSessionRepository;
import waterflow.application.usecases.startwaterflowsession.StartWaterFlowSessionRepository;
import waterflow.application.usecases.syncwaterflowsession.SyncWaterFlowSessionRepository;
import waterflow.domain.entities.WaterContainer;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.entities.WaterPump;
import waterflow.domain.entities.WaterSource;
import waterflow.domain.exceptions.WaterContainerNotFoundException;
import waterflow.domain.exceptions.WaterPumpNotFoundException;
import waterflow.domain.exceptions.WaterSourceNotFoundException;
import waterflow.infra.panache.entities.PanacheWaterContainerEntity;
import waterflow.infra.panache.entities.PanacheWaterFlowSessionEntity;
import waterflow.infra.panache.entities.PanacheWaterPumpEntity;
import waterflow.infra.panache.entities.PanacheWaterSourceEntity;
import waterflow.infra.panache.repositories.projections.WaterSessionIDProjection;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class PanacheWaterFlowSessionRepository implements
    CreateWaterFlowSessionRepository,
    StartWaterFlowSessionRepository,
    CompleteWaterFlowSessionRepository,
    SyncWaterFlowSessionRepository,
    FindWaterFlowSessionRepository
{
  private final PanacheWaterSourceRepository sourceRepository;
  private final PanacheWaterContainerRepository containerRepository;
  private final PanacheWaterPumpRepository pumpRepository;

  @Inject
  PanacheWaterFlowSessionRepository(
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
    PanacheWaterFlowSessionEntity.persist(this.toPersistence(session));
  }

  @Override
  public WaterSource findSourceById(ID id) {
    return this.sourceRepository.findSourceById(id);
  }

  @Override
  public WaterContainer findContainerById(ID id) {
    return this.containerRepository.findContainerById(id);
  }

  public List<ID> findActiveIds() {
    PanacheQuery<WaterSessionIDProjection> query =
        PanacheWaterFlowSessionEntity.find(
            "status = :status",
            Parameters.with("status", "ON"
            )
        ).project(WaterSessionIDProjection.class);
    return query.stream().map((r) -> UUID.from(r.id())).collect(Collectors.toList());
  }

  private PanacheWaterFlowSessionEntity toPersistence(WaterFlowSession session) {
    PanacheUserEntity user =
        (PanacheUserEntity) PanacheUserEntity.findByIdOptional(
            session.getUserId().getValue()
        ).orElseThrow(UserNotFoundException::new);

    Optional<PanacheWaterFlowSessionEntity> optional = PanacheWaterFlowSessionEntity.findByIdOptional(
        session.getId().getValue()
    );

    if (optional.isEmpty()) {
      PanacheWaterSourceEntity source =
          (PanacheWaterSourceEntity) PanacheWaterSourceEntity.findByIdOptional(
              session.getWaterSource().getId().getValue()
          ).orElseThrow(WaterSourceNotFoundException::new);

      PanacheWaterContainerEntity container =
          (PanacheWaterContainerEntity) PanacheWaterContainerEntity.findByIdOptional(
              session.getWaterContainer().getId().getValue()
          ).orElseThrow(WaterContainerNotFoundException::new);

      PanacheWaterPumpEntity pump =
          (PanacheWaterPumpEntity) PanacheWaterPumpEntity.findByIdOptional(
              session.getWaterPump().getId().getValue()
          ).orElseThrow(WaterPumpNotFoundException::new);

      return new PanacheWaterFlowSessionEntity(
          session.getId().getValue(),
          session.getStartedAt().map(DateTime::toLocalDateTime).orElse(null),
          session.getFinishedAt().map(DateTime::toLocalDateTime).orElse(null),
          session.getStatus(),
          user,
          source,
          container,
          pump,
          session.getCreatedAt().toLocalDateTime(),
          session.getUpdatedAt().toLocalDateTime()
      );
    }

    PanacheWaterFlowSessionEntity entity = optional.get();
    entity.setStartedAt(session.getStartedAt().map(DateTime::toLocalDateTime).orElse(null));
    entity.setFinishedAt(session.getFinishedAt().map(DateTime::toLocalDateTime).orElse(null));
    entity.setStatus(session.getStatus());
    entity.setUser(user);
    entity.setSource(this.sourceRepository.toPersistence(session.getWaterSource()));
    entity.setContainer(this.containerRepository.toPersistence(session.getWaterContainer()));
    entity.setPump(this.pumpRepository.toPersistence(session.getWaterPump()));

    return entity;
  }

  private WaterFlowSession toModel(PanacheWaterFlowSessionEntity entity) {
    WaterFlowSession session = new WaterFlowSession(
        UUID.from(entity.getId()),
        this.pumpRepository.toModel(entity.getPump()),
        this.sourceRepository.toModel(entity.getSource()),
        this.containerRepository.toModel(entity.getContainer()),
        UUID.from(entity.getUser().getId()),
        entity.getStartedAt() != null ? DateTime.parse(entity.getStartedAt().toString()) : null,
        entity.getFinishedAt() != null ? DateTime.parse(entity.getFinishedAt().toString()) : null,
        entity.getStatus()
    );
    session.setCreatedAt(DateTime.parse(entity.getCreatedAt().toString()));
    session.setUpdatedAt(DateTime.parse(entity.getUpdatedAt().toString()));
    return session;
  }
}
