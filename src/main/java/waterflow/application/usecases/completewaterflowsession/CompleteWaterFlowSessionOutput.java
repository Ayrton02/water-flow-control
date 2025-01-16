package waterflow.application.usecases.completewaterflowsession;

import core.valueobjects.DateTime;
import waterflow.domain.entities.WaterFlowSession;

public class CompleteWaterFlowSessionOutput {
    private final String createdAt;
    private final String startedAt;
    private final String finishedAt;
    private final String status;
    private final String id;
    private final String userId;

    private CompleteWaterFlowSessionOutput(String id, String createdAt, String startedAt, String finishedAt, String status, String userId) {
        this.id = id;
        this.createdAt = createdAt;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.status = status;
        this.userId = userId;
    }

    static CompleteWaterFlowSessionOutput from(WaterFlowSession session) {
        return new CompleteWaterFlowSessionOutput(
            session.getId().getValue(),
            session.getCreatedAt().toString(),
            session.getStartedAt().map(DateTime::toString).orElse(null),
            session.getFinishedAt().map(DateTime::toString).orElse(null),
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

    public String getFinishedAt() {
        return finishedAt;
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
