package waterflow.application.usecases.createwatercontainer;

import waterflow.domain.entities.WaterContainer;

public class CreateWaterContainerOutput {
    private final String id;
    private final Double maxCapacity;
    private final Double currentCapacity;
    private final String type;
    private final String createdAt;
    private final String updatedAt;

    private CreateWaterContainerOutput(
            String id,
            Double maxCapacity,
            Double currentCapacity,
            String type,
            String createdAt,
            String updatedAt
    ) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    static CreateWaterContainerOutput with(WaterContainer container) {
        return new CreateWaterContainerOutput(
            container.getId().getValue(),
            container.getMaxCapacity().getValue(),
            container.getCurrentVolume().getValue(),
            container.getVolumeType().name(),
            container.getCreatedAt().toString(),
            container.getUpdatedAt().toString()
        );
    }

    public String getId() {
        return id;
    }

    public Double getMaxCapacity() {
        return maxCapacity;
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

    public String getUpdatedAt() {
        return updatedAt;
    }
}
