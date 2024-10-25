package waterflow.application.usecases.createwaterflowsession;

import core.valueobjects.ID;

public class CreateWaterFlowSessionInput {
    private final ID pumpId;
    private final ID containerId;
    private final ID sourceId;

    private CreateWaterFlowSessionInput(ID pumpId, ID containerId, ID sourceId) {
        this.pumpId = pumpId;
        this.containerId = containerId;
        this.sourceId = sourceId;
    }

    public static CreateWaterFlowSessionInput with(ID pumpId, ID containerId, ID sourceId) {
        return new CreateWaterFlowSessionInput(
                pumpId,
                containerId,
                sourceId
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
