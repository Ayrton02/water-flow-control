package waterflow.domain.enums;

import java.util.concurrent.TimeUnit;

public enum TimeMeasurementUnit {
    MILLISECONDS(TimeUnit.MILLISECONDS, "ms"),
    SECONDS(TimeUnit.SECONDS, "s"),
    MINUTES(TimeUnit.MINUTES, "min"),
    HOURS(TimeUnit.HOURS, "h");

    TimeMeasurementUnit(TimeUnit timeUnit, String shortDescription) {
        this.timeUnit = timeUnit;
        this.shortDescription = shortDescription;
    }

    private final TimeUnit timeUnit;
    private final String shortDescription;

    public long convert(long sourceDuration, TimeMeasurementUnit sourceUnit) {
        return this.timeUnit.convert(sourceDuration, sourceUnit.timeUnit);
    }

    public String getShortDescription() {
        return this.shortDescription;
    }
}
