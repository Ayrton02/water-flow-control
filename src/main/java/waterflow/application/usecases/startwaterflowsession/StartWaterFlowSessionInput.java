package waterflow.application.usecases.startwaterflowsession;

import core.valueobjects.ID;

public class StartWaterFlowSessionInput {
    private final ID id;

    private StartWaterFlowSessionInput(ID id) {
        this.id = id;
    }

    public static StartWaterFlowSessionInput with(ID id) {
        return new StartWaterFlowSessionInput(id);
    }

    public ID getId() {
        return id;
    }
}
