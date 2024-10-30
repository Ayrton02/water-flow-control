package waterflow.application.usecases.createwaterpump;

import waterflow.domain.entities.WaterPump;

public interface CreateWaterPumpRepository {
    void save(WaterPump pump);
}
