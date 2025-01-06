package waterflow.application.usecases.startwaterflowsession;

import waterflow.domain.entities.WaterFlowSession;

public class StartWaterFlowSessionOutput {
    private final String createdAt;
    private final String startedAt;
    private final String status;
    private final String id;
    private final String userId;

    private StartWaterFlowSessionOutput(
        String id,
        String createdAt,
        String startedAt,
        String status,
        String userId
        ) {
        this.id = id;
        this.createdAt = createdAt;
        this.startedAt = startedAt;
        this.status = status;
        this.userId = userId;
    }

    static StartWaterFlowSessionOutput from(WaterFlowSession session) {
        return new StartWaterFlowSessionOutput(
            session.getId().getValue(),
            session.getCreatedAt().toString(),
            session.getStartedAt().toString(),
            session.getStatus(),
            session.getUserId().getValue()
        );
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getStartedAt() {
        return startedAt;
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
