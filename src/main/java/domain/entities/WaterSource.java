package domain.entities;

import core.BaseEntity;
import domain.valueobjects.Volume;
import domain.valueobjects.ID;
import domain.valueobjects.UUID;

public abstract class WaterSource<T extends Volume<T>> extends BaseEntity {
    private final T maxCapacity;
    private T currentVolume;

    WaterSource(T maxCapacity, T currentVolume) {
        super(UUID.generate());
        this.maxCapacity = maxCapacity;
        this.currentVolume = currentVolume;
    }

    WaterSource(ID id, T maxCapacity, T currentVolume) {
        super(id);
        this.maxCapacity = maxCapacity;
        this.currentVolume = currentVolume;
    }
}
