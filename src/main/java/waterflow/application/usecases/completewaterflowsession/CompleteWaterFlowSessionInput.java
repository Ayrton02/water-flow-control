package waterflow.application.usecases.completewaterflowsession;

import core.valueobjects.ID;
import core.valueobjects.UUID;

public class CompleteWaterFlowSessionInput {
    private final ID id;

    private CompleteWaterFlowSessionInput(String id) {
        this.id = UUID.from(id);
    }

    public static CompleteWaterFlowSessionInput with(String id) {
        return new CompleteWaterFlowSessionInput(id);
    }

    public ID getId() {
        return id;
    }
}
