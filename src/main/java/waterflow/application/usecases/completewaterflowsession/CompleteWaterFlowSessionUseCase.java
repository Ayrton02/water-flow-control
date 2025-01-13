package waterflow.application.usecases.completewaterflowsession;

import infra.logger.Logger;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.exceptions.WaterSessionNotFoundException;

public class CompleteWaterFlowSessionUseCase implements ICompleteWaterFlowSessionUseCase {
    private final Logger logger;
    private final CompleteWaterFlowSessionRepository repository;

    public CompleteWaterFlowSessionUseCase( Logger logger, CompleteWaterFlowSessionRepository repository) {
        this.logger = logger;
        this.repository = repository;
    }

    public CompleteWaterFlowSessionOutput execute(CompleteWaterFlowSessionInput input) {
        this.logger.info("Completing water flow session %s", input);
        WaterFlowSession session = this.repository.findById(input.getId());
        if (session == null) {
            this.logger.error("Water flow session not found %s", input);
            throw new WaterSessionNotFoundException();
        }

        session.complete();
        this.repository.save(session, session.getWaterPump());
        this.logger.info("Water flow session completed %s", input);
        return CompleteWaterFlowSessionOutput.from(session);
    }
}
