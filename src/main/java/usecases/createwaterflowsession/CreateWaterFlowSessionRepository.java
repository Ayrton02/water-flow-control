package usecases.createwaterflowsession;

import domain.entities.WaterContainer;
import domain.entities.WaterFlowSession;
import domain.entities.WaterPump;
import domain.entities.WaterSource;
import domain.valueobjects.ID;
import domain.valueobjects.Volume;

public interface CreateWaterFlowSessionRepository<T extends Volume<T>> {
    WaterPump<T> findPumpById(ID id);
    void save(WaterFlowSession session);
    WaterSource<T> findSourceById(ID id);
    WaterContainer<T> findContainerById(ID id);
}
