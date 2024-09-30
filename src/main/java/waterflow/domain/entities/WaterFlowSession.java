package waterflow.domain.entities;

import core.baseclasses.BaseEntity;
import waterflow.domain.exceptions.WaterFlowSessionException;
import waterflow.domain.exceptions.ContainerAlreadyFullException;
import waterflow.domain.exceptions.SafetyThresholdException;
import core.valueobjects.ID;
import core.valueobjects.UUID;
import waterflow.domain.valueobjects.Volume;

import java.time.LocalDateTime;

public class WaterFlowSession extends BaseEntity {
    private final WaterPump WATER_PUMP;
    private final WaterSource WATER_SOURCE;
    private final WaterContainer WATER_CONTAINER;

    private enum WaterFlowSessionStatus {
        OFF,
        PAUSED,
        ON,
        COMPLETED,
        ERRORED
    }

    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private WaterFlowSessionStatus status;

    private <T extends Volume<T>> WaterFlowSession(
            ID id,
            WaterContainer<T> container,
            WaterSource<T> source,
            WaterPump<T> pump
    ) {
        super(id);
        this.WATER_CONTAINER = container;
        this.WATER_SOURCE = source;
        this.WATER_PUMP = pump;
        this.status = WaterFlowSessionStatus.OFF;
    }

    public static <T extends Volume<T>> WaterFlowSession create(
            WaterSource<T> source,
            WaterContainer<T> container,
            WaterPump<T> pump
    ) {
        return new WaterFlowSession(UUID.generate(), container, source, pump);
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public WaterContainer getWaterContainer() {
        return this.WATER_CONTAINER;
    }

    public WaterPump getWaterPump() {
        return this.WATER_PUMP;
    }

    public WaterSource getWaterSource() {
        return this.WATER_SOURCE;
    }

    public void start() {
        if (this.status != WaterFlowSessionStatus.OFF) {
            throw new WaterFlowSessionException(
                    "cannot start water flow session because status is "+ this.status.name()
            );
        }
        this.startedAt = LocalDateTime.now();

        if (this.WATER_SOURCE.getCurrentVolume().compareTo(this.WATER_SOURCE.getSafetyThreshold()) < 0) {
            this.status = WaterFlowSessionStatus.ERRORED;
            this.finishedAt = LocalDateTime.now();
            throw new SafetyThresholdException("safety threshold reached, try again later");
        }

        if (this.WATER_CONTAINER.getCurrentVolume().compareTo(this.WATER_CONTAINER.getMaxCapacity()) >= 0) {
            this.status = WaterFlowSessionStatus.ERRORED;
            this.finishedAt = LocalDateTime.now();
            throw new ContainerAlreadyFullException("container is already full");
        }

        this.WATER_PUMP.start();
        this.status = WaterFlowSessionStatus.ON;
    }

    public void complete() {
        if (this.status != WaterFlowSessionStatus.ON) {
            throw new WaterFlowSessionException(
                    "cannot complete water flow session because status is "+ this.status.name()
            );
        }

        this.finishedAt = LocalDateTime.now();
        this.WATER_PUMP.stop();
        this.status = WaterFlowSessionStatus.COMPLETED;
    }
}
