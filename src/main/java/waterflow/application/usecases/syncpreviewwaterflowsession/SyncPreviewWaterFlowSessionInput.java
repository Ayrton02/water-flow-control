package waterflow.application.usecases.syncpreviewwaterflowsession;

import core.valueobjects.DateTime;
import core.valueobjects.ID;

public record SyncPreviewWaterFlowSessionInput(ID id, DateTime startDate, DateTime endDate) { }
