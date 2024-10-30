package waterflow.domain.valueobjects;

import core.valueobjects.DateTime;
import waterflow.domain.enums.TimeMeasurementUnit;

import java.time.temporal.ChronoUnit;

public class LiterFlow extends VolumeFlow<Liter> {
    public LiterFlow(Liter liter, TimeMeasurementUnit timeUnit) {
        super(liter, timeUnit);
    }

    public LiterFlow(Liter liter) {
        super(liter, TimeMeasurementUnit.SECONDS);
    }

    @Override
    public Liter calculateFlowByTimeElapsed(DateTime startDate, DateTime endDate) {
        long millisecondsElapsed = DateTime.between(startDate, endDate, ChronoUnit.MILLIS);
        Long timeUnitConvertedElapsed = this.timeUnit.convert(millisecondsElapsed, TimeMeasurementUnit.MILLISECONDS);
        return new Liter(this.volume.getValue() * timeUnitConvertedElapsed);
    }

    @Override
    public String getUnit() {
        return this.volume.getValue().toString() + "L/" + this.timeUnit.getShortDescription();
    }
}
