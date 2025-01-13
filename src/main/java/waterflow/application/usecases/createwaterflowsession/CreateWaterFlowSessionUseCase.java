package waterflow.application.usecases.createwaterflowsession;

import infra.logger.Logger;
import user.application.usecases.finduser.FindUserRepository;
import user.domain.entities.User;
import user.domain.exceptions.UserNotFoundException;
import waterflow.domain.entities.WaterContainer;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.entities.WaterPump;
import waterflow.domain.entities.WaterSource;
import waterflow.domain.exceptions.WaterContainerNotFoundException;
import waterflow.domain.exceptions.WaterPumpNotFoundException;
import waterflow.domain.exceptions.WaterSourceNotFoundException;

public class CreateWaterFlowSessionUseCase implements ICreateWaterFlowSessionUseCase {
    private final Logger logger;
    private final CreateWaterFlowSessionRepository repository;
    private final FindUserRepository findUserRepository;

    public CreateWaterFlowSessionUseCase(
        Logger logger,
        CreateWaterFlowSessionRepository repository,
        FindUserRepository findUserRepository
    ) {
        this.logger = logger;
        this.repository = repository;
        this.findUserRepository = findUserRepository;
    }
    public CreateWaterFlowSessionOutput execute(CreateWaterFlowSessionInput input) {
        this.logger.info("Creating water flow session %s", input);
        WaterPump waterPump = this.repository.findPumpById(input.getPumpId());
        WaterSource waterSource = this.repository.findSourceById(input.getSourceId());
        WaterContainer waterContainer = this.repository.findContainerById(input.getContainerId());
        User user = this.findUserRepository.findUser(input.getUserId());

        if (waterPump == null) {
            this.logger.error("Water pump was not found %s", input);
            throw new WaterPumpNotFoundException();
        }

        if (waterSource == null) {
            this.logger.error("Water source was not found %s", input);
            throw new WaterSourceNotFoundException();
        }

        if (waterContainer == null) {
            this.logger.error("Water container was not found %s", input);
            throw new WaterContainerNotFoundException();
        }

        if (user == null) {
            this.logger.error("User was not found %s", input);
            throw new UserNotFoundException();
        }

        WaterFlowSession session = WaterFlowSession.create(waterSource, waterContainer, waterPump, user.getId());

        this.repository.save(session);
        this.logger.info("Water flow session created %s", session);
        return CreateWaterFlowSessionOutput.from(session);
    }
}
