package usecases.syncwaterflowsession;

import domain.entities.WaterContainer;
import domain.entities.WaterFlowSession;
import domain.entities.WaterPump;
import domain.entities.WaterSource;
import domain.valueobjects.ID;

public interface SyncWaterFlowSessionRepository {
    WaterFlowSession findById(ID id);
    void save(WaterFlowSession session, WaterContainer container, WaterSource source, WaterPump pump);
}
