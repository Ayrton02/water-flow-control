package waterflow.infra.panache.repositories;

import core.valueobjects.DateTime;
import core.valueobjects.ID;
import core.valueobjects.UUID;
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
    Optional<PanacheWaterPumpEntity> entity = PanacheWaterPumpEntity.findByIdOptional(id.getValue());
    return entity.map(this::toModel).orElse(null);
  }

  @Transactional
  public void save(WaterPump pump) {
    PanacheWaterPumpEntity.persist(this.toPersistence(pump));
  }

  WaterPump toModel(PanacheWaterPumpEntity entity) {
    WaterPump pump = WaterPumpFactory.createWaterPump(
        UUID.from(entity.getId()),
        entity.getVolume(),
        TimeMeasurementUnit.valueOf(entity.getTimeUnit().toUpperCase()),
        VolumeType.valueOf(entity.getType().toUpperCase()),
        entity.getActive()
    );
    pump.setCreatedAt(DateTime.parse(entity.getCreatedAt().toString()));
    pump.setUpdatedAt(DateTime.parse(entity.getUpdatedAt().toString()));
    return pump;
  }

  PanacheWaterPumpEntity toPersistence(WaterPump model) {
    Optional<PanacheWaterPumpEntity> entity = PanacheWaterPumpEntity.findByIdOptional(model.getId().getValue());

    return entity.map((e) -> {
      e.setActive(model.isActive());
      e.setVolume(model.getVolumeFlow().getVolume().getValue());
      return e;
    }).orElse(
        new PanacheWaterPumpEntity(
            model.getId().getValue(),
            model.getVolumeType().name(),
            model.isActive(),
            model.getVolumeFlow().getVolume().getValue(),
            model.getVolumeFlow().getTimeUnit().name(),
            model.getCreatedAt().toLocalDateTime(),
            model.getUpdatedAt().toLocalDateTime()
        )
    );
  }
}
