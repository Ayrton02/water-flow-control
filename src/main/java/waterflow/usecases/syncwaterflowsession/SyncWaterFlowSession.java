package usecases.syncwaterflowsession;

import domain.entities.WaterFlowSession;
import domain.valueobjects.ID;
import usecases.startwaterflowsession.WaterSessionNotFoundException;

public class SyncWaterFlowSession {

    private SyncWaterFlowSessionRepository repository;

    SyncWaterFlowSession(SyncWaterFlowSessionRepository repository) {
        this.repository = repository;
    }

    public void execute(ID id) {
        WaterFlowSession session = this.repository.findById(id);
        if (session == null) {
            throw new WaterSessionNotFoundException();
        }

    }
}
