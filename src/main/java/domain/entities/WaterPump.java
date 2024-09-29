package domain.entities;

import core.BaseEntity;
import domain.exceptions.ContainerAlreadyFullException;
import domain.exceptions.SafetyThresholdException;
import domain.valueobjects.ID;
import domain.valueobjects.UUID;
import domain.valueobjects.Volume;
import domain.valueobjects.VolumeFlow;

import java.time.LocalDateTime;

public abstract class WaterPump<T extends Volume<T>> extends BaseEntity {
    private VolumeFlow<T> volumeFlow;
    private boolean isActive = false;

    protected WaterPump(
            ID id,
            VolumeFlow<T> volumeFlow
    ) {
        super(id);
        this.volumeFlow = volumeFlow;
    }

    protected WaterPump(
            VolumeFlow<T> volumeFlow
    ) {
        super(UUID.generate());
        this.volumeFlow = volumeFlow;
    }

    public void start() {
        this.isActive = true;
    }

    public void stop() {
        this.isActive = false;
    }

    public boolean isActive() {
        return this.isActive;
    }
}
