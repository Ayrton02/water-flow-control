package waterflow.domain.valueobjects;

import core.valueobjects.DateTime;
import waterflow.domain.enums.TimeMeasurementUnit;

public abstract class VolumeFlow<T extends Volume<T>> {
        protected final T volume;
        protected final TimeMeasurementUnit timeUnit;

        VolumeFlow(T volume, TimeMeasurementUnit timeUnit) {
            this.volume = volume;
            this.timeUnit = timeUnit;
        }

        public T getVolume() {
                return volume;
        }

        public TimeMeasurementUnit getTimeUnit() {
                return timeUnit;
        }

        /**
         * Calculates volume flow over a period of time
         *
         * @param startDate start date of flow
         * @param endDate end date of flow
         */
        public abstract T calculateFlowByTimeElapsed(DateTime startDate, DateTime endDate);
        public abstract String getUnit();
}
