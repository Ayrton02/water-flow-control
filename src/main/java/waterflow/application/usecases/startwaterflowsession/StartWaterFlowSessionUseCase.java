package waterflow.application.usecases.startwaterflowsession;

import core.exception.BaseException;
import waterflow.domain.entities.WaterFlowSession;
import core.valueobjects.ID;
import waterflow.domain.exceptions.WaterSessionNotFoundException;


public class StartWaterFlowSessionUseCase implements IStartWaterFlowSessionUseCase {

    private StartWaterFlowSessionRepository repository;

    public StartWaterFlowSessionUseCase(StartWaterFlowSessionRepository repository) {
        this.repository = repository;
    }

    public StartWaterFlowSessionOutput execute(StartWaterFlowSessionInput input) {
        WaterFlowSession session = this.repository.findById(input.getId());

        if (session == null) {
            throw new WaterSessionNotFoundException();
        }

        try {
            session.start();
        } catch (BaseException exception) {
            //Logging
        }

        this.repository.save(session);
        return StartWaterFlowSessionOutput.from(session);
    }
}
