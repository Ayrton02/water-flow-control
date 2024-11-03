package waterflow.application.usecases.createwaterflowsession;

import core.valueobjects.ID;

public class CreateWaterFlowSessionInput {
    private final ID pumpId;
    private final ID containerId;
    private final ID sourceId;
    private final ID userId;

    private CreateWaterFlowSessionInput(ID pumpId, ID containerId, ID sourceId, ID userId) {
        this.pumpId = pumpId;
        this.containerId = containerId;
        this.sourceId = sourceId;
        this.userId = userId;
    }

    public static CreateWaterFlowSessionInput with(ID pumpId, ID containerId, ID sourceId, ID userId) {
        return new CreateWaterFlowSessionInput(
                pumpId,
                containerId,
                sourceId,
                userId
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

    public ID getUserId() {
        return userId;
    }
}
