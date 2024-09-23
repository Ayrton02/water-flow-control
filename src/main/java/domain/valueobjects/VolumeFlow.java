package domain.valueobjects;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public abstract class WaterFlow<T extends Volume<T>> {
        private final T volume;
        private final TimeUnit timeUnit;

        WaterFlow(T volume, TimeUnit timeUnit) {
            this.volume = volume;
            this.timeUnit = timeUnit;
        }

        public T getVolume() {
                return volume;
        }

        public TimeUnit getTimeUnit() {
                return timeUnit;
        }

        /**
         * Calculates volume flow over a period of time
         *
         * @param startDate start date of flow
         * @param endDate end date
         */
        public abstract T calculateFlowByTimeElapsed(LocalDateTime startDate, LocalDateTime endDate);
}
