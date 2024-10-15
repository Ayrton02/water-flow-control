package waterflow.usecases.completewaterflowsession;

import core.valueobjects.ID;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.entities.WaterPump;

public interface CompleteWaterFlowSessionRepository {
    WaterFlowSession findById(ID id);
    void save(WaterFlowSession session, WaterPump pump);
}
