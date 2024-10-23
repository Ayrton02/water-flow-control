package waterflow.application.usecases.createwaterflowsession;

import waterflow.domain.entities.WaterContainer;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.entities.WaterPump;
import waterflow.domain.entities.WaterSource;
import core.valueobjects.ID;
import waterflow.domain.valueobjects.Volume;

public interface CreateWaterFlowSessionRepository<T extends Volume<T>> {
    WaterPump<T> findPumpById(ID id);
    void save(WaterFlowSession session);
    WaterSource<T> findSourceById(ID id);
    WaterContainer<T> findContainerById(ID id);
}
