package waterflow.usecases.syncwaterflowsession;

import waterflow.domain.entities.WaterContainer;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.entities.WaterPump;
import waterflow.domain.entities.WaterSource;
import core.valueobjects.ID;

public interface SyncWaterFlowSessionRepository {
    WaterFlowSession findById(ID id);
    void save(WaterFlowSession session, WaterContainer container, WaterSource source, WaterPump pump);
}
