package domain.entities;

import core.BaseEntity;
import domain.exceptions.WaterOverFlowException;
import domain.valueobjects.Capacity;
import domain.valueobjects.ID;
import domain.valueobjects.UUID;

public abstract class WaterContainer<T extends Capacity<T>> extends BaseEntity {
    private final T maxCapacity;
    private T currentCapacity;

    protected WaterContainer(T maxCapacity, T currentCapacity) {
        super(UUID.generate());
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    protected WaterContainer(ID id, T maxCapacity, T currentCapacity) {
        super(id);
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    public T getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(T currentVolume) {
        this.currentCapacity = currentVolume;
    }

    public T getMaxCapacity() {
        return maxCapacity;
    }

    public void fill(T quantity) throws WaterOverFlowException {
        if (
                quantity.add(this.currentCapacity).getValue() > this.maxCapacity.getValue()
        ) {
            throw new WaterOverFlowException("water is overflowing");
        }

        this.currentCapacity = quantity;
    }

    public void empty() {
        this.currentCapacity.empty();
    }
}
