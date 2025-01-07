package waterflow.application.usecases.createwatercontainer;

import waterflow.domain.enums.VolumeType;

public class CreateWaterContainerInput {
    private final Double maxCapacity;
    private final Double currentCapacity;
    private final VolumeType type;

    private CreateWaterContainerInput(
            Double maxCapacity,
            Double currentCapacity,
            VolumeType type
    ) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
        this.type = type;
    }

    public static CreateWaterContainerInput with(
            Double maxCapacity,
            Double currentCapacity,
            VolumeType type
    ) {
        return new CreateWaterContainerInput(
                maxCapacity,
                currentCapacity,
                type
        );
    }

    public Double getMaxCapacity() {
        return maxCapacity;
    }

    public Double getCurrentCapacity() {
        return currentCapacity;
    }

    public VolumeType getType() {
        return type;
    }
}
