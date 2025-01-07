package waterflow.application.usecases.findwaterflowsession;

import core.valueobjects.ID;

public class FindWaterFlowSessionInput {
    private final ID id;

    private FindWaterFlowSessionInput(ID id) {
        this.id = id;
    }

    public static FindWaterFlowSessionInput with(ID id) {
        return new FindWaterFlowSessionInput(id);
    }

    public ID getId() {
        return id;
    }
}
