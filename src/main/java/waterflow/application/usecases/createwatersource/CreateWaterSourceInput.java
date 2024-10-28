package waterflow.application.usecases.createwatersource;

import core.valueobjects.ID;
import waterflow.domain.enums.VolumeType;

public class CreateWaterSourceInput {
    private final ID id;
    private final Double maxCapacity;
    private final Double safetyThreshold;
    private final Double currentCapacity;
    private final VolumeType type;

    private CreateWaterSourceInput(
            ID id,
            Double maxCapacity,
            Double safetyThreshold,
            Double currentCapacity,
            VolumeType type
    ) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.safetyThreshold = safetyThreshold;
        this.currentCapacity = currentCapacity;
        this.type = type;
    }

    static CreateWaterSourceInput with(
            ID id,
            Double maxCapacity,
            Double safetyThreshold,
            Double currentCapacity,
            VolumeType type
    ) {
        return new CreateWaterSourceInput(
                id,
                maxCapacity,
                safetyThreshold,
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

    public Double getSafetyThreshold() {
        return safetyThreshold;
    }

    public Double getCurrentCapacity() {
        return currentCapacity;
    }

    public VolumeType getType() {
        return type;
    }
}
