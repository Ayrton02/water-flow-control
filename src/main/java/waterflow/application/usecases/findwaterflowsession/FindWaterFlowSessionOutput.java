package waterflow.application.usecases.findwaterflowsession;

import core.valueobjects.DateTime;
import waterflow.domain.entities.WaterFlowSession;

public class FindWaterFlowSessionOutput {
    private final String id;
    private final String startedAt;
    private final String finishedAt;
    private final String status;
    private final String containerId;
    private final String sourceId;
    private final String pumpId;
    private final String userId;
    private final String createdAt;
    private final String updatedAt;

    private FindWaterFlowSessionOutput(
        String id,
        String startedAt,
        String finishedAt,
        String status,
        String containerId,
        String sourceId,
        String pumpId,
        String userId,
        String createdAt,
        String updatedAt
    ) {
        this.id = id;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.status = status;
        this.containerId = containerId;
        this.sourceId = sourceId;
        this.pumpId = pumpId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    static FindWaterFlowSessionOutput from(WaterFlowSession session) {
        return new FindWaterFlowSessionOutput(
            session.getId().getValue(),
            session.getStartedAt().map(DateTime::toString).orElse(null),
            session.getFinishedAt().map(DateTime::toString).orElse(null),
            session.getStatus(),
            session.getWaterContainer().getId().getValue(),
            session.getWaterSource().getId().getValue(),
            session.getWaterPump().getId().getValue(),
            session.getUserId().getValue(),
            session.getCreatedAt().toString(),
            session.getUpdatedAt().toString()
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

    public String getContainerId() {
        return containerId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getPumpId() {
        return pumpId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
