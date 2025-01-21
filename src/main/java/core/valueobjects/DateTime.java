package core.valueobjects;

import waterflow.domain.enums.TimeMeasurementUnit;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateTime {
    private final LocalDateTime value;

    private DateTime(LocalDateTime time) {
        this.value = time;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    public boolean isAfter(DateTime date) {
        return this.value.isAfter(date.value);
    }

    public boolean isBefore(DateTime date) {
        return this.value.isBefore(date.value);
    }

    public LocalDateTime plus(Long amount, TimeMeasurementUnit unit) {
        return this.value.plus(amount, unit.getTimeUnit().toChronoUnit());
    }

    public LocalDateTime minus(Long amount, TimeMeasurementUnit unit) {
        return this.value.minus(amount, unit.getTimeUnit().toChronoUnit());
    }

    public LocalDateTime toLocalDateTime() {
        return this.value;
    }

    public static DateTime now() {
        return new DateTime(LocalDateTime.now(Clock.systemDefaultZone()));
    }

    public static DateTime parse(String date) {
        return new DateTime(LocalDateTime.parse(date));
    }

    public static long between(DateTime start, DateTime end, ChronoUnit unit) {
        return unit.between(start.value, end.value);
    }
}
