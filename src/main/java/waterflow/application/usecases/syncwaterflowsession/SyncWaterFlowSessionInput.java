package waterflow.application.usecases.syncwaterflowsession;

import core.valueobjects.ID;
import core.valueobjects.UUID;

public class SyncWaterFlowSessionInput {
    private final ID id;

    private SyncWaterFlowSessionInput(ID id) {
        this.id = id;
    }

    public static SyncWaterFlowSessionInput with(String id) {
        return new SyncWaterFlowSessionInput(
                UUID.from(id)
        );
    }

    public ID getId() {
        return id;
    }
}
