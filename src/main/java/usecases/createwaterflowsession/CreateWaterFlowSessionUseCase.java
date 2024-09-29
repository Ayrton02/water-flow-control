package usecases.createwaterflowsession;

import domain.entities.WaterContainer;
import domain.entities.WaterFlowSession;
import domain.entities.WaterPump;
import domain.entities.WaterSource;
import domain.valueobjects.ID;

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
