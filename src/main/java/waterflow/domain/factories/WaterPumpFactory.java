package waterflow.domain.factories;

import core.valueobjects.ID;
import waterflow.domain.entities.LiterWaterPump;
import waterflow.domain.entities.WaterPump;
import waterflow.domain.enums.TimeMeasurementUnit;
import waterflow.domain.enums.VolumeType;
import waterflow.domain.valueobjects.Liter;
import waterflow.domain.valueobjects.LiterFlow;

public class WaterPumpFactory {
    public static WaterPump createWaterPump(
            Double volume,
            TimeMeasurementUnit timeUnit,
            VolumeType type,
            Boolean isActive
    ) {
        if (type == VolumeType.LITER) {
            return new LiterWaterPump(
                new LiterFlow(new Liter(volume), timeUnit),
                isActive
            );
        }
        throw new RuntimeException("Type not supported");
    }

    public static WaterPump createWaterPump(
        ID id,
        Double volume,
        TimeMeasurementUnit timeUnit,
        VolumeType type,
        Boolean isActive
    ) {
        if (type == VolumeType.LITER) {
            return new LiterWaterPump(
                id,
                new LiterFlow(new Liter(volume), timeUnit),
                isActive
            );
        }
        throw new RuntimeException("Type not supported");
    }
}
