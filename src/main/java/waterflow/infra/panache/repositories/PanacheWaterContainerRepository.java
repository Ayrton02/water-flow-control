package waterflow.infra.panache.repositories;

import core.valueobjects.DateTime;
import core.valueobjects.ID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
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

  @Transactional
  public void save(WaterContainer container) {
    PanacheWaterContainerEntity.persist(this.toEntity(container));
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

  PanacheWaterContainerEntity toEntity(WaterContainer model) {
    return new PanacheWaterContainerEntity(
        model.getId().toString(),
        model.getVolumeType().name(),
        model.getMaxCapacity().getValue(),
        model.getCurrentVolume().getValue(),
        model.getCreatedAt().toLocalDateTime(),
        model.getUpdatedAt().toLocalDateTime()
    );
  }
}
