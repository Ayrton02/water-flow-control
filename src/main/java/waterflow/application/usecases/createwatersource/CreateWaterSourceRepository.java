package waterflow.application.usecases.createwatersource;

import waterflow.domain.entities.WaterSource;

public interface CreateWaterSourceRepository {
    void save(WaterSource source);
}
