package waterflow.application.usecases.createwatercontainer;

import waterflow.domain.entities.WaterContainer;

public interface CreateWaterContainerRepository {
    void save(WaterContainer container);
}
