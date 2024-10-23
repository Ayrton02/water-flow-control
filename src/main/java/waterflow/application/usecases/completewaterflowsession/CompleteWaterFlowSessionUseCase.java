package waterflow.application.usecases.completewaterflowsession;

import core.valueobjects.ID;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.exceptions.WaterSessionNotFoundException;

public class CompleteWaterFlowSessionUseCase {

    private CompleteWaterFlowSessionRepository repository;

    CompleteWaterFlowSessionUseCase(CompleteWaterFlowSessionRepository repository) {
        this.repository = repository;
    }

    public void execute(ID id) {
        WaterFlowSession session = this.repository.findById(id);
        if (session == null) {
            throw new WaterSessionNotFoundException();
        }

        session.complete();
        this.repository.save(session, session.getWaterPump());
    }
}
