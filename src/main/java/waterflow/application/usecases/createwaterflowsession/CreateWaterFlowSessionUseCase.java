package waterflow.application.usecases.createwaterflowsession;

import waterflow.domain.entities.WaterContainer;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.entities.WaterPump;
import waterflow.domain.entities.WaterSource;
import core.valueobjects.ID;
import waterflow.domain.exceptions.WaterContainerNotFoundException;
import waterflow.domain.exceptions.WaterPumpNotFoundException;
import waterflow.domain.exceptions.WaterSourceNotFoundException;

public class CreateWaterFlowSessionUseCase implements ICreateWaterFlowSessionUseCase {

    private final CreateWaterFlowSessionRepository repository;

    public CreateWaterFlowSessionUseCase(CreateWaterFlowSessionRepository repository) {
        this.repository = repository;
    }
    public CreateWaterFlowSessionOutput execute(CreateWaterFlowSessionInput input) {
        WaterPump waterPump = this.repository.findPumpById(input.getPumpId());
        WaterSource waterSource = this.repository.findSourceById(input.getSourceId());
        WaterContainer waterContainer = this.repository.findContainerById(input.getContainerId());

        if (waterPump == null) {
            throw new WaterPumpNotFoundException();
        }

        if (waterSource == null) {
            throw new WaterSourceNotFoundException();
        }

        if (waterContainer == null) {
            throw new WaterContainerNotFoundException();
        }

        WaterFlowSession session = WaterFlowSession.create(waterSource, waterContainer, waterPump);

        this.repository.save(session);
        return CreateWaterFlowSessionOutput.from(session);
    }
}
