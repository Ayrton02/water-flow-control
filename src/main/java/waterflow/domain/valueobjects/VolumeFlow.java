package waterflow.domain.valueobjects;

import core.valueobjects.DateTime;

import java.util.concurrent.TimeUnit;

public abstract class VolumeFlow<T extends Volume<T>> {
        protected final T volume;
        protected final TimeUnit timeUnit;

        VolumeFlow(T volume, TimeUnit timeUnit) {
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
         * @param endDate end date of flow
         */
        public abstract T calculateFlowByTimeElapsed(DateTime startDate, DateTime endDate);
}
