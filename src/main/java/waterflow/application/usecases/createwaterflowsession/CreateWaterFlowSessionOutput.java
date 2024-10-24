package waterflow.application.usecases.createwaterflowsession;

import waterflow.domain.entities.WaterFlowSession;

public class CreateWaterFlowSessionOutput {
    private final String createdAt;
    private final String status;
    private final String id;

    private CreateWaterFlowSessionOutput(String id, String createdAt, String status) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
    }

    static CreateWaterFlowSessionOutput from(WaterFlowSession session) {
        return new CreateWaterFlowSessionOutput(
                session.getId().getValue(),
                session.getCreatedAt().toString(),
                session.getStatus()
        );
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }
}
