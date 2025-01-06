package waterflow.infra.panache.repositories;

import core.valueobjects.DateTime;
import core.valueobjects.ID;
import jakarta.enterprise.context.ApplicationScoped;
import waterflow.domain.entities.WaterContainer;
import waterflow.domain.enums.VolumeType;
import waterflow.domain.factories.WaterContainerFactory;
import waterflow.infra.panache.entities.PanacheWaterContainerEntity;

import java.util.Optional;

@ApplicationScoped
public class PanacheWaterContainerRepository {

  public WaterContainer findContainerById(ID id) {
    Optional<PanacheWaterContainerEntity> entity = PanacheWaterContainerEntity.find("id", id.getValue()).firstResultOptional();
    return entity.map(this::toModel).orElse(null);
  }

  WaterContainer toModel(PanacheWaterContainerEntity entity) {
    WaterContainer container = WaterContainerFactory.createWaterContainer(
        entity.getMaxCapacity(),
        entity.getCurrentVolume(),
        VolumeType.fromString(entity.getType())
    );
    container.setCreatedAt(DateTime.parse(entity.getCreatedAt().toString()));
    container.setUpdatedAt(DateTime.parse(entity.getUpdatedAt().toString()));
    return container;
  }
}
