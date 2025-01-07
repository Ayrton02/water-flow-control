package waterflow.application.usecases.findwaterflowsession;

import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.exceptions.WaterSessionNotFoundException;

public class FindWaterFlowSessionUseCase implements IFindWaterFlowSessionUseCase {

    private FindWaterFlowSessionRepository repository;

    public FindWaterFlowSessionUseCase(FindWaterFlowSessionRepository repository) {
        this.repository = repository;
    }

    public FindWaterFlowSessionOutput execute(FindWaterFlowSessionInput input) {
        WaterFlowSession session = this.repository.findById(input.getId());
        if (session == null) {
            throw new WaterSessionNotFoundException();
        }

        return FindWaterFlowSessionOutput.from(session);
    }
}
