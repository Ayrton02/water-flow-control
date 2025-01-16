package waterflow.application.usecases.syncpreviewwaterflowsession;

import infra.logger.Logger;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.exceptions.WaterSessionNotFoundException;

public class SyncPreviewWaterFlowSessionUseCase implements ISyncPreviewWaterFlowSessionUseCase {
  private final Logger logger;
  private final SyncPreviewWaterFlowSessionRepository repository;

  public SyncPreviewWaterFlowSessionUseCase(Logger logger, SyncPreviewWaterFlowSessionRepository repository) {
    this.logger = logger;
    this.repository = repository;
  }

  @Override
  public SyncPreviewWaterFlowSessionOutput execute(SyncPreviewWaterFlowSessionInput input) {
    this.logger.info("Preview for session %s", input.id());
    WaterFlowSession session = this.repository.findById(input.id());
    if (session == null) {
      throw new WaterSessionNotFoundException();
    }

    session.syncPreview(input.startDate(), input.endDate());

    return SyncPreviewWaterFlowSessionOutput.with(session);
  }
}
