package waterflow.application.usecases.syncwaterflowsession;

import waterflow.domain.entities.WaterFlowSession;

public class SyncWaterFlowSessionOutput {
    private final String createdAt;
    private final String startedAt;
    private final String status;
    private final String id;
    private final Double containerVolume;
    private final Double sourceVolume;

    private SyncWaterFlowSessionOutput(String id, String createdAt, String startedAt, String status, Double containerVolume, Double sourceVolume) {
        this.id = id;
        this.createdAt = createdAt;
        this.startedAt = startedAt;
        this.status = status;
        this.containerVolume = containerVolume;
        this.sourceVolume = sourceVolume;
    }

    static SyncWaterFlowSessionOutput from(WaterFlowSession session) {
        return new SyncWaterFlowSessionOutput(
                session.getId().getValue(),
                session.getCreatedAt().toString(),
                session.getStartedAt().toString(),
                session.getStatus(),
                session.getWaterContainer().getCurrentVolume().getValue(),
                session.getWaterSource().getCurrentVolume().getValue()
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

    public Double getContainerVolume() {
        return containerVolume;
    }

    public Double getSourceVolume() {
        return sourceVolume;
    }
}
