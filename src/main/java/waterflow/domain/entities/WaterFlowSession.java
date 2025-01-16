package waterflow.domain.entities;

import core.baseclasses.BaseEntity;
import core.exception.BaseException;
import core.valueobjects.DateTime;
import core.valueobjects.ID;
import core.valueobjects.UUID;
import waterflow.domain.exceptions.*;
import waterflow.domain.valueobjects.Volume;
import waterflow.domain.valueobjects.VolumeFlow;

import java.util.Optional;

public class WaterFlowSession extends BaseEntity {
    private final WaterPump WATER_PUMP;
    private final WaterSource WATER_SOURCE;
    private final WaterContainer WATER_CONTAINER;
    private final ID userId;

    private enum WaterFlowSessionStatus {
        OFF,
        PAUSED,
        ON,
        COMPLETED,
        ERRORED,
        COMPLETED_WITH_ERROR
    }

    private DateTime startedAt;
    private DateTime finishedAt;
    private WaterFlowSessionStatus status;

    private <T extends Volume<T>> WaterFlowSession(
            ID id,
            WaterContainer<T> container,
            WaterSource<T> source,
            WaterPump<T> pump,
            ID userId
    ) {
        super(id);
        this.WATER_CONTAINER = container;
        this.WATER_SOURCE = source;
        this.WATER_PUMP = pump;
        this.status = WaterFlowSessionStatus.OFF;
        this.userId = userId;
    }

    public WaterFlowSession(
        ID id,
        WaterPump WATER_PUMP,
        WaterSource WATER_SOURCE,
        WaterContainer WATER_CONTAINER,
        ID userId,
        DateTime startedAt,
        DateTime finishedAt,
        String status
    ) {
        super(id);
        this.WATER_PUMP = WATER_PUMP;
        this.WATER_SOURCE = WATER_SOURCE;
        this.WATER_CONTAINER = WATER_CONTAINER;
        this.userId = userId;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.status = WaterFlowSessionStatus.valueOf(status.toUpperCase());
    }

    public static <T extends Volume<T>> WaterFlowSession create(
            WaterSource<T> source,
            WaterContainer<T> container,
            WaterPump<T> pump,
            ID userId
    ) {
        return new WaterFlowSession(UUID.generate(), container, source, pump, userId);
    }

    public Optional<DateTime> getStartedAt() {
        return Optional.ofNullable(startedAt);
    }

    public Optional<DateTime> getFinishedAt() {
        return Optional.ofNullable(finishedAt);
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

    public String getStatus() {
        return this.status.name();
    }

    public ID getUserId() {
        return userId;
    }

    public void start() {
        if (this.status != WaterFlowSessionStatus.OFF) {
            throw new WaterFlowSessionException(
                    "cannot start water flow session because status is "+ this.status.name()
            );
        }
        this.startedAt = DateTime.now();

        if (this.WATER_SOURCE.getCurrentVolume().compareTo(this.WATER_SOURCE.getSafetyThreshold()) < 0) {
            this.status = WaterFlowSessionStatus.ERRORED;
            this.finishedAt = DateTime.now();
            throw new SafetyThresholdException("safety threshold reached, try again later");
        }

        if (this.WATER_CONTAINER.getCurrentVolume().compareTo(this.WATER_CONTAINER.getMaxCapacity()) >= 0) {
            this.status = WaterFlowSessionStatus.ERRORED;
            this.finishedAt = DateTime.now();
            throw new ContainerAlreadyFullException("container is already full");
        }

        if (this.WATER_PUMP.isActive()) {
            this.status = WaterFlowSessionStatus.ERRORED;
            this.finishedAt = DateTime.now();
            throw new WaterPumpInUseException("water pump is in use");
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

        this.finishedAt = DateTime.now();
        this.WATER_PUMP.stop();
        this.status = WaterFlowSessionStatus.COMPLETED;
    }

    public void sync() {
        if (this.WATER_PUMP.isActive() && this.status == WaterFlowSessionStatus.ON) {
            VolumeFlow flow = this.WATER_PUMP.getVolumeFlow();
            Volume volume = flow.calculateFlowByTimeElapsed(this.startedAt, DateTime.now());
            try {
                this.WATER_SOURCE.dump(volume);
                this.WATER_CONTAINER.fill(volume);
            } catch (BaseException e) {
                if (e instanceof SafetyThresholdException) {
                    this.WATER_SOURCE.setCurrentVolume(this.WATER_SOURCE.getSafetyThreshold());
                }

                if (e instanceof WaterOverFlowException) {
                    this.WATER_CONTAINER.setCurrentVolume(this.WATER_CONTAINER.getMaxCapacity());
                }

                this.finishedAt = DateTime.now();
                this.WATER_PUMP.stop();
                this.status = WaterFlowSessionStatus.COMPLETED_WITH_ERROR;
            }
        }
    }

    @Override
    public String toString() {
        return String.format(
            "WaterFlowSession[" +
                "id=%s, " +
                "waterPump=%s, " +
                "waterSource=%s, " +
                "waterContainer=%s, " +
                "userId=%s, " +
                "startedAt=%s, " +
                "finishedAt=%s, " +
                "status=%s, " +
                "createdAt=%s, " +
                "updatedAt=%s" +
                "]",
            this.getId().toString(),
            this.getWaterPump().toString(),
            this.getWaterSource().toString(),
            this.getWaterContainer().toString(),
            this.userId.toString(),
            this.getStartedAt().map(DateTime::toString).orElse(null),
            this.getFinishedAt().map(DateTime::toString).orElse(null),
            this.status.name(),
            this.getCreatedAt().toString(),
            this.getUpdatedAt().toString()
        );
    }
}
