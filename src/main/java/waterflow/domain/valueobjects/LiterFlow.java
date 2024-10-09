package waterflow.domain.valueobjects;

import core.valueobjects.DateTime;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class LiterFlow extends VolumeFlow<Liter> {
    public LiterFlow(Liter liter, TimeUnit timeUnit) {
        super(liter, timeUnit);
    }

    public LiterFlow(Liter liter) {
        super(liter, TimeUnit.SECONDS);
    }

    @Override
    public Liter calculateFlowByTimeElapsed(DateTime startDate, DateTime endDate) {
        long millisecondsElapsed = DateTime.between(startDate, endDate, ChronoUnit.MILLIS);
        Long timeUnitConvertedElapsed = this.timeUnit.convert(millisecondsElapsed, this.timeUnit);
        return new Liter(this.volume.getValue() * timeUnitConvertedElapsed);
    }
}
