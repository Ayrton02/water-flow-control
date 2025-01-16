package waterflow.application.usecases.syncpreviewwaterflowsession;

import core.valueobjects.ID;
import waterflow.domain.entities.WaterFlowSession;

public interface SyncPreviewWaterFlowSessionRepository {
  WaterFlowSession findById(ID id);
}
