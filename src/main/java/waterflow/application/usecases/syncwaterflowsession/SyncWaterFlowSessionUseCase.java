package waterflow.application.usecases.syncwaterflowsession;

import infra.logger.Logger;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.exceptions.WaterSessionNotFoundException;

public class SyncWaterFlowSessionUseCase implements ISyncWaterFlowSessionUseCase{
    private final Logger logger;
    private final SyncWaterFlowSessionRepository repository;

    public SyncWaterFlowSessionUseCase(Logger logger, SyncWaterFlowSessionRepository repository) {
      this.logger = logger;
      this.repository = repository;
    }

    public SyncWaterFlowSessionOutput execute(SyncWaterFlowSessionInput input) {
        this.logger.info("Syncing water flow session %s", input);
        WaterFlowSession session = this.repository.findById(input.getId());
        if (session == null) {
            this.logger.error("Water flow session was not found %s", input);
            throw new WaterSessionNotFoundException();
        }

        session.sync();
        this.repository.save(session, session.getWaterContainer(), session.getWaterSource(), session.getWaterPump());
        this.logger.info("Water flow session synced %s", session);
        return SyncWaterFlowSessionOutput.from(session);
    }
}
