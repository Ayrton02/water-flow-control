package waterflow.application.usecases.syncpreviewwaterflowsession;

import waterflow.domain.entities.WaterFlowSession;

public record SyncPreviewWaterFlowSessionOutput(
    String id,
    Double containerVolume,
    Double containerMaxCapacity,
    Double sourceVolume,
    Double sourceSafetyThreshold,
    String status
) {
  public static SyncPreviewWaterFlowSessionOutput with(WaterFlowSession session) {
    return new SyncPreviewWaterFlowSessionOutput(
        session.getId().getValue(),
        session.getWaterContainer().getCurrentVolume().getValue(),
        session.getWaterContainer().getMaxCapacity().getValue(),
        session.getWaterSource().getCurrentVolume().getValue(),
        session.getWaterSource().getSafetyThreshold().getValue(),
        session.getStatus()
    );
  }
}
