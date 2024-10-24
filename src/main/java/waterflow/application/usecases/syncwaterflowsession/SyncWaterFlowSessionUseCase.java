package waterflow.application.usecases.syncwaterflowsession;

import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.exceptions.WaterSessionNotFoundException;

public class SyncWaterFlowSessionUseCase implements ISyncWaterFlowSessionUseCase{

    private SyncWaterFlowSessionRepository repository;

    SyncWaterFlowSessionUseCase(SyncWaterFlowSessionRepository repository) {
        this.repository = repository;
    }

    public SyncWaterFlowSessionOutput execute(SyncWaterFlowSessionInput input) {
        WaterFlowSession session = this.repository.findById(input.getId());
        if (session == null) {
            throw new WaterSessionNotFoundException();
        }

        session.sync();
        this.repository.save(session, session.getWaterContainer(), session.getWaterSource(), session.getWaterPump());
        return SyncWaterFlowSessionOutput.from(session);
    }
}
