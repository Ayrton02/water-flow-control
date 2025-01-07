package waterflow.infra.panache.repositories;

import core.valueobjects.DateTime;
import core.valueobjects.ID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import waterflow.application.usecases.createwaterpump.CreateWaterPumpRepository;
import waterflow.domain.entities.WaterPump;
import waterflow.domain.enums.TimeMeasurementUnit;
import waterflow.domain.enums.VolumeType;
import waterflow.domain.factories.WaterPumpFactory;
import waterflow.infra.panache.entities.PanacheWaterPumpEntity;

import java.util.Optional;

@ApplicationScoped
public class PanacheWaterPumpRepository implements CreateWaterPumpRepository {

  public WaterPump findPumpById(ID id) {
    Optional<PanacheWaterPumpEntity> entity = PanacheWaterPumpEntity.find("id", id.getValue()).firstResultOptional();
    return entity.map(this::toModel).orElse(null);
  }

  @Transactional
  public void save(WaterPump pump) {
    PanacheWaterPumpEntity.persist(this.toEntity(pump));
  }

  WaterPump toModel(PanacheWaterPumpEntity entity) {
    WaterPump pump = WaterPumpFactory.createWaterPump(
        entity.getVolume(),
        TimeMeasurementUnit.fromString(entity.getTimeUnit()),
        VolumeType.fromString(entity.getType())
    );
    pump.setCreatedAt(DateTime.parse(entity.getCreatedAt().toString()));
    pump.setUpdatedAt(DateTime.parse(entity.getUpdatedAt().toString()));
    return pump;
  }

  PanacheWaterPumpEntity toEntity(WaterPump model) {
    return new PanacheWaterPumpEntity(
        model.getId().toString(),
        model.getVolumeType().name(),
        model.getVolumeFlow().getVolume().getValue(),
        model.getVolumeFlow().getTimeUnit().name(),
        model.getCreatedAt().toLocalDateTime(),
        model.getUpdatedAt().toLocalDateTime()
    );
  }
}
