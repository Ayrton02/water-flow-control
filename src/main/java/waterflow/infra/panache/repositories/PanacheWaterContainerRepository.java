package waterflow.infra.panache.repositories;

import core.valueobjects.DateTime;
import core.valueobjects.ID;
import core.valueobjects.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import waterflow.application.usecases.createwatercontainer.CreateWaterContainerRepository;
import waterflow.domain.entities.WaterContainer;
import waterflow.domain.enums.VolumeType;
import waterflow.domain.factories.WaterContainerFactory;
import waterflow.infra.panache.entities.PanacheWaterContainerEntity;

import java.util.Optional;

@ApplicationScoped
public class PanacheWaterContainerRepository implements CreateWaterContainerRepository {

  public WaterContainer findContainerById(ID id) {
    Optional<PanacheWaterContainerEntity> entity = PanacheWaterContainerEntity.findByIdOptional(id.getValue());
    return entity.map(this::toModel).orElse(null);
  }

  @Transactional
  public void save(WaterContainer container) {
    PanacheWaterContainerEntity.persist(this.toPersistence(container));
  }

  WaterContainer toModel(PanacheWaterContainerEntity entity) {
    WaterContainer container = WaterContainerFactory.createWaterContainer(
        UUID.from(entity.getId()),
        entity.getMaxCapacity(),
        entity.getCurrentVolume(),
        VolumeType.valueOf(entity.getType().toUpperCase())
    );
    container.setCreatedAt(DateTime.parse(entity.getCreatedAt().toString()));
    container.setUpdatedAt(DateTime.parse(entity.getUpdatedAt().toString()));
    return container;
  }

  PanacheWaterContainerEntity toPersistence(WaterContainer model) {
    Optional<PanacheWaterContainerEntity> entity = PanacheWaterContainerEntity.findByIdOptional(model.getId().getValue());

    return entity.map((e) -> {
      e.setCurrentVolume(model.getCurrentVolume().getValue());
      e.setMaxCapacity(model.getMaxCapacity().getValue());
      return e;
    }).orElse(
        new PanacheWaterContainerEntity(
            model.getId().getValue(),
            model.getVolumeType().name(),
            model.getMaxCapacity().getValue(),
            model.getCurrentVolume().getValue(),
            model.getCreatedAt().toLocalDateTime(),
            model.getUpdatedAt().toLocalDateTime()
        )
    );
  }
}
