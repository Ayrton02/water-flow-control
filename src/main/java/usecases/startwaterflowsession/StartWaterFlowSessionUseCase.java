package usecases.startwaterflowsession;

import core.BaseException;
import domain.entities.WaterFlowSession;
import domain.valueobjects.ID;


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
