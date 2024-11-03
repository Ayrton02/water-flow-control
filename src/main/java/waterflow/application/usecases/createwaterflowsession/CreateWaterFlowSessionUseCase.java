package waterflow.application.usecases.createwaterflowsession;

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

    private final CreateWaterFlowSessionRepository repository;
    private final FindUserRepository findUserRepository;

    public CreateWaterFlowSessionUseCase(
            CreateWaterFlowSessionRepository repository,
            FindUserRepository findUserRepository
    ) {
        this.repository = repository;
        this.findUserRepository = findUserRepository;
    }
    public CreateWaterFlowSessionOutput execute(CreateWaterFlowSessionInput input) {
        WaterPump waterPump = this.repository.findPumpById(input.getPumpId());
        WaterSource waterSource = this.repository.findSourceById(input.getSourceId());
        WaterContainer waterContainer = this.repository.findContainerById(input.getContainerId());
        User user = this.findUserRepository.findUser(input.getUserId());

        if (waterPump == null) {
            throw new WaterPumpNotFoundException();
        }

        if (waterSource == null) {
            throw new WaterSourceNotFoundException();
        }

        if (waterContainer == null) {
            throw new WaterContainerNotFoundException();
        }

        if (user == null) {
            throw new UserNotFoundException();
        }

        WaterFlowSession session = WaterFlowSession.create(waterSource, waterContainer, waterPump, user.getId());

        this.repository.save(session);
        return CreateWaterFlowSessionOutput.from(session);
    }
}
