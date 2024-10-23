package waterflow.application.usecases.startwaterflowsession;

import core.valueobjects.ID;
import core.valueobjects.UUID;

public class StartWaterFlowSessionInput {
    private final ID id;

    private StartWaterFlowSessionInput(ID id) {
        this.id = id;
    }

    public static StartWaterFlowSessionInput with(String id) {
        return new StartWaterFlowSessionInput(
                UUID.from(id)
        );
    }

    public ID getId() {
        return id;
    }
}
