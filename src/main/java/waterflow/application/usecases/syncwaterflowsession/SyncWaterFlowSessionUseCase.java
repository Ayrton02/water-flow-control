package waterflow.application.usecases.syncwaterflowsession;

import core.valueobjects.ID;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.exceptions.WaterSessionNotFoundException;

public class SyncWaterFlowSessionUseCase {

    private SyncWaterFlowSessionRepository repository;

    SyncWaterFlowSessionUseCase(SyncWaterFlowSessionRepository repository) {
        this.repository = repository;
    }

    public void execute(ID id) {
        WaterFlowSession session = this.repository.findById(id);
        if (session == null) {
            throw new WaterSessionNotFoundException();
        }

        session.sync();
        this.repository.save(session, session.getWaterContainer(), session.getWaterSource(), session.getWaterPump());
    }
}
