package waterflow.domain.factories;

import waterflow.domain.entities.LiterWaterSource;
import waterflow.domain.entities.WaterSource;
import waterflow.domain.enums.VolumeType;
import waterflow.domain.valueobjects.Liter;

public class WaterSourceFactory {
    public static WaterSource createWaterSource(
            Double maxCapacity,
            Double safetyThreshold,
            Double currentCapacity,
            VolumeType type
    ) {
        if (type == VolumeType.LITER) {
            return new LiterWaterSource(
                    new Liter(maxCapacity),
                    new Liter(safetyThreshold),
                    new Liter(currentCapacity)
            );
        }

        throw new RuntimeException("Type not supported");
    }

}
