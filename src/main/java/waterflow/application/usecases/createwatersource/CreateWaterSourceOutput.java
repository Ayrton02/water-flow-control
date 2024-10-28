package waterflow.application.usecases.createwatersource;

import waterflow.domain.entities.WaterSource;

public class CreateWaterSourceOutput {
    private final String id;
    private final Double maxCapacity;
    private final Double safetyThreshold;
    private final Double currentCapacity;
    private final String type;
    private final String createdAt;

    private CreateWaterSourceOutput(
            String id,
            Double maxCapacity,
            Double safetyThreshold,
            Double currentCapacity,
            String type,
            String createdAt
    ) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.safetyThreshold = safetyThreshold;
        this.currentCapacity = currentCapacity;
        this.type = type;
        this.createdAt = createdAt;
    }

    static CreateWaterSourceOutput with(WaterSource source) {
        return new CreateWaterSourceOutput(
                source.getId().getValue(),
                source.getMaxCapacity().getValue(),
                source.getSafetyThreshold().getValue(),
                source.getCurrentVolume().getValue(),
                source.getVolumeType().name(),
                source.getCreatedAt().toString()
        );
    }

    public String getId() {
        return id;
    }

    public Double getMaxCapacity() {
        return maxCapacity;
    }

    public Double getSafetyThreshold() {
        return safetyThreshold;
    }

    public Double getCurrentCapacity() {
        return currentCapacity;
    }

    public String getType() {
        return type;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
