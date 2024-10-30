package waterflow.domain.factories;

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
            VolumeType type
    ) {
        if (type == VolumeType.LITER) {
            return new LiterWaterPump(
                    new LiterFlow(new Liter(volume), timeUnit)
            );
        }

        throw new RuntimeException("Type not supported");
    }

}
