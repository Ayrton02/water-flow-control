package domain.valueobjects;

import java.time.LocalDateTime;
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
    public Liter calculateFlowByTimeElapsed(LocalDateTime startDate, LocalDateTime endDate) {
        long millisecondsElapsed = ChronoUnit.MILLIS.between(startDate, endDate);
        Long timeUnitConvertedElapsed = this.timeUnit.convert(millisecondsElapsed, this.timeUnit);
        return new Liter(this.volume.getValue() * timeUnitConvertedElapsed);
    }
}
