package waterflow.application.usecases.syncwaterflowsession;

import core.valueobjects.ID;

public class SyncWaterFlowSessionInput {
    private final ID id;

    private SyncWaterFlowSessionInput(ID id) {
        this.id = id;
    }

    public static SyncWaterFlowSessionInput with(ID id) {
        return new SyncWaterFlowSessionInput(id);
    }

    public ID getId() {
        return id;
    }
}
