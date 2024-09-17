package domain.entities;

import core.BaseEntity;
import domain.valueobjects.Capacity;
import domain.valueobjects.ID;
import domain.valueobjects.UUID;

public abstract class WaterSource<T extends Capacity<T>> extends BaseEntity {
    private final T maxCapacity;
    private T currentCapacity;

    WaterSource(T maxCapacity, T currentCapacity) {
        super(UUID.generate());
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    WaterSource(ID id, T maxCapacity, T currentCapacity) {
        super(id);
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }
}
