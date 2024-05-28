package domain.entities;

import core.BaseEntity;
import domain.exceptions.WaterOverFlowException;
import domain.valueobjects.Capacity;
import domain.valueobjects.ID;
import domain.valueobjects.UUID;
import domain.valueobjects.Volume;

public class WaterContainer extends BaseEntity {
    private final Capacity maxCapacity;
    private Capacity currentCapacity;

    static WaterContainer create(Capacity maxCapacity, Capacity currentCapacity) {
        return new WaterContainer(
                UUID.generate(),
                maxCapacity,
                currentCapacity
        );
    }

    private WaterContainer(ID id, Capacity maxCapacity, Capacity currentCapacity) {
        super(id);
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    public Capacity getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(Capacity currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public Capacity getMaxCapacity() {
        return maxCapacity;
    }

    public void fill(Capacity quantity) throws WaterOverFlowException {
        if (
                quantity.add(this.currentCapacity).getValue() > this.maxCapacity.getValue()
        ) {
            throw new WaterOverFlowException("water is overflowing");
        }

        this.currentCapacity = quantity;
    }

    public void empty() {
        this.currentCapacity = new Volume(0d,0d,0d);
    }
}
