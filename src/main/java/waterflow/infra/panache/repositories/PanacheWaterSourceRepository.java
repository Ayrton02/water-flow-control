package waterflow.infra.panache.repositories;

import core.valueobjects.DateTime;
import core.valueobjects.ID;
import jakarta.enterprise.context.ApplicationScoped;
import waterflow.domain.entities.WaterSource;
import waterflow.domain.enums.VolumeType;
import waterflow.domain.factories.WaterSourceFactory;
import waterflow.infra.panache.entities.PanacheWaterSourceEntity;

import java.util.Optional;

@ApplicationScoped
public class PanacheWaterSourceRepository {

  public WaterSource findSourceById(ID id) {
    Optional<PanacheWaterSourceEntity> entity = PanacheWaterSourceEntity.find("id", id.getValue()).firstResultOptional();
    return entity.map(this::toModel).orElse(null);
  }

  WaterSource toModel(PanacheWaterSourceEntity entity) {
    WaterSource source = WaterSourceFactory.createWaterSource(
        entity.getMaxCapacity(),
        entity.getSafetyThreshold(),
        entity.getCurrentVolume(),
        VolumeType.fromString(entity.getType())
    );
    source.setCreatedAt(DateTime.parse(entity.getCreatedAt().toString()));
    source.setUpdatedAt(DateTime.parse(entity.getUpdatedAt().toString()));
    return source;
  }
}
