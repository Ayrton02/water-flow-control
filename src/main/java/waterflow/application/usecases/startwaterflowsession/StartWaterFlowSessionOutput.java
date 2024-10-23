package waterflow.application.usecases.startwaterflowsession;

import waterflow.domain.entities.WaterFlowSession;

public class StartWaterFlowSessionOutput {
    private final String createdAt;
    private final String startedAt;
    private final String status;
    private final String id;

    private StartWaterFlowSessionOutput(String id, String createdAt, String startedAt, String status) {
        this.id = id;
        this.createdAt = createdAt;
        this.startedAt = startedAt;
        this.status = status;
    }

    static StartWaterFlowSessionOutput from(WaterFlowSession session) {
        return new StartWaterFlowSessionOutput(
                session.getId().getValue(),
                session.getCreatedAt().toString(),
                session.getStartedAt().toString(),
                session.getStatus()
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
}
