package waterflow.application.usecases.completewaterflowsession;

import core.valueobjects.ID;

public class CompleteWaterFlowSessionInput {
    private final ID id;

    private CompleteWaterFlowSessionInput(ID id) {
        this.id = id;
    }

    public static CompleteWaterFlowSessionInput with(ID id) {
        return new CompleteWaterFlowSessionInput(id);
    }

    public ID getId() {
        return id;
    }
}
