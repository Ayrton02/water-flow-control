package waterflow.application.usecases.createwatercontainer;

import core.valueobjects.ID;
import waterflow.domain.enums.VolumeType;

public class CreateWaterContainerInput {
    private final ID id;
    private final Double maxCapacity;
    private final Double currentCapacity;
    private final VolumeType type;

    private CreateWaterContainerInput(
            ID id,
            Double maxCapacity,
            Double currentCapacity,
            VolumeType type
    ) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
        this.type = type;
    }

    static CreateWaterContainerInput with(
            ID id,
            Double maxCapacity,
            Double currentCapacity,
            VolumeType type
    ) {
        return new CreateWaterContainerInput(
                id,
                maxCapacity,
                currentCapacity,
                type
        );
    }

    public ID getId() {
        return id;
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
