package waterflow.application.usecases.createwaterflowsession;

import waterflow.domain.entities.WaterFlowSession;

public class CreateWaterFlowSessionOutput {
    private final String createdAt;
    private final String status;
    private final String id;
    private final String userId;

    private CreateWaterFlowSessionOutput(String id, String createdAt, String status, String userId) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
        this.userId = userId;
    }

    static CreateWaterFlowSessionOutput from(WaterFlowSession session) {
        return new CreateWaterFlowSessionOutput(
                session.getId().getValue(),
                session.getCreatedAt().toString(),
                session.getStatus(),
                session.getUserId().getValue()
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

    public String getUserId() {
        return userId;
    }
}
