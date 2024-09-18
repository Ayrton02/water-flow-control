package domain.valueobjects;

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
}
