package waterflow.application.usecases.completewaterflowsession;

import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.exceptions.WaterSessionNotFoundException;

public class CompleteWaterFlowSessionUseCase implements ICompleteWaterFlowSessionUseCase {

    private CompleteWaterFlowSessionRepository repository;

    public CompleteWaterFlowSessionUseCase(CompleteWaterFlowSessionRepository repository) {
        this.repository = repository;
    }

    public CompleteWaterFlowSessionOutput execute(CompleteWaterFlowSessionInput input) {
        WaterFlowSession session = this.repository.findById(input.getId());
        if (session == null) {
            throw new WaterSessionNotFoundException();
        }

        session.complete();
        this.repository.save(session, session.getWaterPump());
        return CompleteWaterFlowSessionOutput.from(session);
    }
}
