package waterflow.application.usecases.createwatersource;

import core.valueobjects.ID;
import waterflow.domain.enums.VolumeType;

public class CreateWaterSourceInput {
    private final Double maxCapacity;
    private final Double safetyThreshold;
    private final Double currentCapacity;
    private final VolumeType type;

    private CreateWaterSourceInput(
            Double maxCapacity,
            Double safetyThreshold,
            Double currentCapacity,
            VolumeType type
    ) {
        this.maxCapacity = maxCapacity;
        this.safetyThreshold = safetyThreshold;
        this.currentCapacity = currentCapacity;
        this.type = type;
    }

    public static CreateWaterSourceInput with(
            Double maxCapacity,
            Double safetyThreshold,
            Double currentCapacity,
            VolumeType type
    ) {
        return new CreateWaterSourceInput(
                maxCapacity,
                safetyThreshold,
                currentCapacity,
                type
        );
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
