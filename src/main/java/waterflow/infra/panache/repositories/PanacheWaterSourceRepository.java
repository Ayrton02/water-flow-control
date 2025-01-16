package waterflow.infra.panache.repositories;

import core.valueobjects.DateTime;
import core.valueobjects.ID;
import core.valueobjects.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import waterflow.application.usecases.createwatersource.CreateWaterSourceRepository;
import waterflow.domain.entities.WaterSource;
import waterflow.domain.enums.VolumeType;
import waterflow.domain.factories.WaterSourceFactory;
import waterflow.infra.panache.entities.PanacheWaterSourceEntity;

import java.util.Optional;

@ApplicationScoped
public class PanacheWaterSourceRepository implements CreateWaterSourceRepository {

  public WaterSource findSourceById(ID id) {
    Optional<PanacheWaterSourceEntity> entity = PanacheWaterSourceEntity.findByIdOptional( id.getValue());
    return entity.map(this::toModel).orElse(null);
  }

  @Transactional
  public void save(WaterSource source) {
    PanacheWaterSourceEntity.persist(this.toPersistence(source));
  }

  WaterSource toModel(PanacheWaterSourceEntity entity) {
    WaterSource source = WaterSourceFactory.createWaterSource(
        UUID.from(entity.getId()),
        entity.getMaxCapacity(),
        entity.getSafetyThreshold(),
        entity.getCurrentVolume(),
        VolumeType.valueOf(entity.getType().toUpperCase())
    );
    source.setCreatedAt(DateTime.parse(entity.getCreatedAt().toString()));
    source.setUpdatedAt(DateTime.parse(entity.getUpdatedAt().toString()));
    return source;
  }

  PanacheWaterSourceEntity toPersistence(WaterSource model) {
    Optional<PanacheWaterSourceEntity> optional = PanacheWaterSourceEntity.findByIdOptional(model.getId().getValue());

    return optional.map((o) -> {
      o.setCurrentVolume(model.getCurrentVolume().getValue());
      o.setMaxCapacity((model.getMaxCapacity().getValue()));
      o.setSafetyThreshold((model.getSafetyThreshold().getValue()));
      o.setCurrentVolume((model.getCurrentVolume().getValue()));
      return o;
    }).orElse(
        new PanacheWaterSourceEntity(
            model.getId().getValue(),
            model.getVolumeType().name(),
            model.getMaxCapacity().getValue(),
            model.getSafetyThreshold().getValue(),
            model.getCurrentVolume().getValue(),
            model.getCreatedAt().toLocalDateTime(),
            model.getUpdatedAt().toLocalDateTime()
        )
    );
  }
}
