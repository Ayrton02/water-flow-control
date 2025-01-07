package waterflow.application.usecases.createwaterpump;

import waterflow.domain.entities.WaterPump;

public class CreateWaterPumpOutput {
    private final String id;
    private final String flow;
    private final String type;
    private final Boolean isActive;
    private final String createdAt;
    private final String updatedAt;

    private CreateWaterPumpOutput(
        String id,
        String flow,
        String type,
        Boolean isActive,
        String createdAt,
        String updatedAt
    ) {
        this.id = id;
        this.flow = flow;
        this.type = type;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    static CreateWaterPumpOutput with(WaterPump pump) {
        return new CreateWaterPumpOutput(
            pump.getId().getValue(),
            pump.getVolumeFlow().getUnit(),
            pump.getVolumeType().name(),
            pump.isActive(),
            pump.getCreatedAt().toString(),
            pump.getUpdatedAt().toString());
    }

    public String getId() {
        return id;
    }

    public String getFlow() {
        return flow;
    }

    public String getType() {
        return type;
    }

    public Boolean getActive() {
        return isActive;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
