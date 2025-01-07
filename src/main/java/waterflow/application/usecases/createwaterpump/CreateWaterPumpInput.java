package waterflow.application.usecases.createwaterpump;

import waterflow.domain.enums.TimeMeasurementUnit;
import waterflow.domain.enums.VolumeType;

public class CreateWaterPumpInput {
    private final Double volume;
    private final TimeMeasurementUnit timeUnit;
    private final VolumeType type;

    private CreateWaterPumpInput(Double volume, TimeMeasurementUnit timeUnit, VolumeType type) {
        this.volume = volume;
        this.timeUnit = timeUnit;
        this.type = type;
    }

    public static CreateWaterPumpInput with(
            Double volume,
            TimeMeasurementUnit timeUnit,
            VolumeType type
    ) {
        return new CreateWaterPumpInput(
                volume,
                timeUnit,
                type
        );
    }

    public TimeMeasurementUnit getTimeUnit() {
        return timeUnit;
    }

    public VolumeType getType() {
        return type;
    }

    public Double getVolume() {
        return volume;
    }
}
