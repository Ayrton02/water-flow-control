package waterflow.application.usecases.findwaterflowsession;

import core.valueobjects.ID;
import waterflow.domain.entities.WaterFlowSession;

public interface FindWaterFlowSessionRepository {
    WaterFlowSession findById(ID id);
}
