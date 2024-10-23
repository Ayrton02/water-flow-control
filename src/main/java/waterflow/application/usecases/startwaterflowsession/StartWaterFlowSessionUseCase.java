package waterflow.application.usecases.startwaterflowsession;

import core.exception.BaseException;
import waterflow.domain.entities.WaterFlowSession;
import core.valueobjects.ID;
import waterflow.domain.exceptions.WaterSessionNotFoundException;


public class StartWaterFlowSessionUseCase {

    private StartWaterFlowSessionRepository repository;

    public StartWaterFlowSessionUseCase(StartWaterFlowSessionRepository repository) {
        this.repository = repository;
    }

    public void execute(ID id) {
        WaterFlowSession session = this.repository.findById(id);

        if (session == null) {
            throw new WaterSessionNotFoundException();
        }

        try {
            session.start();
        } catch (BaseException exception) {
            //Logging
        }

        this.repository.save(session);
    }
}
