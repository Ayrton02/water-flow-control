package waterflow.application.usecases.completewaterflowsession;

import waterflow.domain.entities.WaterFlowSession;

public class CompleteWaterFlowSessionOutput {
    private final String createdAt;
    private final String startedAt;
    private final String finishedAt;
    private final String status;
    private final String id;

    private CompleteWaterFlowSessionOutput(String id, String createdAt, String startedAt, String finishedAt, String status) {
        this.id = id;
        this.createdAt = createdAt;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.status = status;
    }

    static CompleteWaterFlowSessionOutput from(WaterFlowSession session) {
        return new CompleteWaterFlowSessionOutput(
                session.getId().getValue(),
                session.getCreatedAt().toString(),
                session.getStartedAt().toString(),
                session.getFinishedAt().toString(),
                session.getStatus()
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
}
