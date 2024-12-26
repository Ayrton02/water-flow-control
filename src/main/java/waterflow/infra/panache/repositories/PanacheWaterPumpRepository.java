package waterflow.infra.panache.repositories;

import core.valueobjects.DateTime;
import core.valueobjects.ID;
import jakarta.enterprise.context.ApplicationScoped;
import waterflow.domain.entities.WaterPump;
import waterflow.domain.enums.TimeMeasurementUnit;
import waterflow.domain.enums.VolumeType;
import waterflow.domain.factories.WaterPumpFactory;
import waterflow.infra.panache.entities.PanacheWaterPumpEntity;

import java.util.Optional;

@ApplicationScoped
public class PanacheWaterPumpRepository {

  public WaterPump findPumpById(ID id) {
    Optional<PanacheWaterPumpEntity> entity = PanacheWaterPumpEntity.find("id", id.getValue()).firstResultOptional();
    return entity.map(this::toModel).orElse(null);
  }

  private WaterPump toModel(PanacheWaterPumpEntity entity) {
    WaterPump pump = WaterPumpFactory.createWaterPump(
        entity.getVolume(),
        TimeMeasurementUnit.fromString(entity.getTimeUnit()),
        VolumeType.fromString(entity.getType())
    );
    pump.setCreatedAt(DateTime.parse(entity.getCreatedAt().toString()));
    pump.setUpdatedAt(DateTime.parse(entity.getUpdatedAt().toString()));
    return pump;
  }
}
