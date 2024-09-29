package usecases.startwaterflowsession;

import domain.entities.WaterFlowSession;
import domain.valueobjects.ID;

public interface StartWaterFlowSessionRepository {
    WaterFlowSession findById(ID id);
    void save(WaterFlowSession session);
}
