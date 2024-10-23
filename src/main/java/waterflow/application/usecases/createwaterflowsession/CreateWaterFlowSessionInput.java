package waterflow.application.usecases.createwaterflowsession;

import core.valueobjects.ID;
import core.valueobjects.UUID;

public class CreateWaterFlowSessionInput {
    private final ID pumpId;
    private final ID containerId;
    private final ID sourceId;

    private CreateWaterFlowSessionInput(ID pumpId, ID containerId, ID sourceId) {
        this.pumpId = pumpId;
        this.containerId = containerId;
        this.sourceId = sourceId;
    }

    public static CreateWaterFlowSessionInput with(String pumpId, String containerId, String sourceId) {
        return new CreateWaterFlowSessionInput(
                UUID.from(pumpId),
                UUID.from(containerId),
                UUID.from(sourceId)
        );
    }

    public ID getPumpId() {
        return pumpId;
    }

    public ID getSourceId() {
        return sourceId;
    }

    public ID getContainerId() {
        return containerId;
    }
}
