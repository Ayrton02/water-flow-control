package waterflow.application.usecases.createwaterpump;

import waterflow.domain.entities.WaterPump;

public class CreateWaterPumpOutput {
    private final String id;
    private final String flow;
    private final String type;
    private final String createdAt;

    private CreateWaterPumpOutput(
            String id,
            String flow,
            String type,
            String createdAt
    ) {
        this.id = id;
        this.flow = flow;
        this.type = type;
        this.createdAt = createdAt;
    }

    static CreateWaterPumpOutput with(WaterPump pump) {
        return new CreateWaterPumpOutput(
                pump.getId().getValue(),
                pump.getVolumeFlow().getUnit(),
                pump.getVolumeType().name(),
                pump.getCreatedAt().toString()
        );
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

    public String getCreatedAt() {
        return createdAt;
    }
}
