package waterflow.application.usecases.startwaterflowsession;

import waterflow.domain.entities.WaterFlowSession;
import core.valueobjects.ID;

public interface StartWaterFlowSessionRepository {
    WaterFlowSession findById(ID id);
    void save(WaterFlowSession session);
}
