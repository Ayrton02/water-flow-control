package waterflow.application.usecases.findwaterflowsession;

import infra.logger.Logger;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.exceptions.WaterSessionNotFoundException;

public class FindWaterFlowSessionUseCase implements IFindWaterFlowSessionUseCase {
    private final Logger logger;
    private final FindWaterFlowSessionRepository repository;

    public FindWaterFlowSessionUseCase(Logger logger, FindWaterFlowSessionRepository repository) {
      this.logger = logger;
      this.repository = repository;
    }

    public FindWaterFlowSessionOutput execute(FindWaterFlowSessionInput input) {
        this.logger.info("Searching for water flow session %s", input);
        WaterFlowSession session = this.repository.findById(input.getId());
        if (session == null) {
            this.logger.error("Water flow session was not found %s", input);
            throw new WaterSessionNotFoundException();
        }

        return FindWaterFlowSessionOutput.from(session);
    }
}
