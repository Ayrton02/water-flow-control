package waterflow.domain.enums;

import java.util.concurrent.TimeUnit;

public enum TimeMeasurementUnit {
    MILLISECONDS(TimeUnit.MILLISECONDS, "ms"),
    SECONDS(TimeUnit.SECONDS, "s"),
    MINUTES(TimeUnit.MINUTES, "min"),
    HOURS(TimeUnit.HOURS, "h");

    private final TimeUnit timeUnit;
    private final String shortDescription;

    TimeMeasurementUnit(TimeUnit timeUnit, String shortDescription) {
        this.timeUnit = timeUnit;
        this.shortDescription = shortDescription;
    }

    public long convert(long sourceDuration, TimeMeasurementUnit sourceUnit) {
        return this.timeUnit.convert(sourceDuration, sourceUnit.timeUnit);
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public static TimeMeasurementUnit fromString(String any) {
        switch (any.toUpperCase()) {
            case "MILLISECONDS": return TimeMeasurementUnit.MILLISECONDS;
            case "SECONDS": return TimeMeasurementUnit.SECONDS;
            case "MINUTES": return TimeMeasurementUnit.MINUTES;
            case "HOURS": return TimeMeasurementUnit.HOURS;
            default: return null;
        }
    }
}
