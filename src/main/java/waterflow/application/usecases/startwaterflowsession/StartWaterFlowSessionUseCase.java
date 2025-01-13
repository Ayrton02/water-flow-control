package waterflow.application.usecases.startwaterflowsession;

import core.exception.BaseException;
import infra.logger.Logger;
import waterflow.domain.entities.WaterFlowSession;
import core.valueobjects.ID;
import waterflow.domain.exceptions.WaterSessionNotFoundException;


public class StartWaterFlowSessionUseCase implements IStartWaterFlowSessionUseCase {
    private final Logger logger;
    private final StartWaterFlowSessionRepository repository;

    public StartWaterFlowSessionUseCase(Logger logger, StartWaterFlowSessionRepository repository) {
      this.logger = logger;
      this.repository = repository;
    }

    public StartWaterFlowSessionOutput execute(StartWaterFlowSessionInput input) {
        this.logger.info("Starting water flow session %s", input);
        WaterFlowSession session = this.repository.findById(input.getId());

        if (session == null) {
            this.logger.error("Water flow session was not found %s", input);
            throw new WaterSessionNotFoundException();
        }

        try {
            session.start();
        } catch (BaseException e) {
            this.logger.warn("Water flow session starting error %s", e);
        }

        this.repository.save(session);
        this.logger.info("Water flow session saved %s", session);
        return StartWaterFlowSessionOutput.from(session);
    }
}
