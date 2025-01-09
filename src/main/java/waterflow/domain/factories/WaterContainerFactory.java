package waterflow.domain.factories;

import core.valueobjects.ID;
import waterflow.domain.entities.LiterWaterContainer;
import waterflow.domain.entities.WaterContainer;
import waterflow.domain.enums.VolumeType;
import waterflow.domain.valueobjects.Liter;

public class WaterContainerFactory {
    public static WaterContainer createWaterContainer(
            Double maxCapacity,
            Double currentCapacity,
            VolumeType type
    ) {
        if (type == VolumeType.LITER) {
            return new LiterWaterContainer(
                    new Liter(maxCapacity),
                    new Liter(currentCapacity)
            );
        }

        throw new RuntimeException("Type not supported");
    }

    public static WaterContainer createWaterContainer(
        ID id,
        Double maxCapacity,
        Double currentCapacity,
        VolumeType type
    ) {
        if (type == VolumeType.LITER) {
            return new LiterWaterContainer(
                id,
                new Liter(maxCapacity),
                new Liter(currentCapacity)
            );
        }

        throw new RuntimeException("Type not supported");
    }
}
