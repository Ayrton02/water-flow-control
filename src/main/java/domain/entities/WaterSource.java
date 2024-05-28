package domain.entities;

import core.BaseEntity;
import domain.valueobjects.Capacity;
import domain.valueobjects.ID;
import domain.valueobjects.UUID;

public class WaterSource extends BaseEntity {
    private final Capacity maxCapacity;
    private Capacity currentCapacity;

    static WaterSource create(Capacity maxCapacity, Capacity currentCapacity) {
        return new WaterSource(
                UUID.generate(),
                maxCapacity,
                currentCapacity
        );
    }

    private WaterSource(ID id, Capacity maxCapacity, Capacity currentCapacity) {
        super(id);
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }
}
