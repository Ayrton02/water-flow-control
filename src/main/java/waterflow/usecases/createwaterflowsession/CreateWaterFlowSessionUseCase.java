package waterflow.usecases.createwaterflowsession;

import waterflow.domain.entities.WaterContainer;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.entities.WaterPump;
import waterflow.domain.entities.WaterSource;
import core.valueobjects.ID;
import waterflow.domain.exceptions.WaterContainerNotFoundException;
import waterflow.domain.exceptions.WaterPumpNotFoundException;
import waterflow.domain.exceptions.WaterSourceNotFoundException;

public class CreateWaterFlowSessionUseCase {

    private final CreateWaterFlowSessionRepository repository;

    public CreateWaterFlowSessionUseCase(CreateWaterFlowSessionRepository repository) {
        this.repository = repository;
    }
    public void execute(ID pumpId, ID containerId, ID sourceId) {
        WaterPump waterPump = this.repository.findPumpById(pumpId);
        WaterSource waterSource = this.repository.findSourceById(sourceId);
        WaterContainer waterContainer = this.repository.findContainerById(containerId);

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
    }
}
