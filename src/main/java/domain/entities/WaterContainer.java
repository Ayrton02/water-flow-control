package domain.entities;

import core.BaseEntity;
import domain.exceptions.WaterOverFlowException;
import domain.valueobjects.Volume;
import domain.valueobjects.ID;
import domain.valueobjects.UUID;

public abstract class WaterContainer<T extends Volume<T>> extends BaseEntity {
    private final T maxCapacity;
    private T currentVolume;

    protected WaterContainer(T maxCapacity, T currentVolume) {
        super(UUID.generate());
        this.maxCapacity = maxCapacity;
        this.currentVolume = currentVolume;
    }

    protected WaterContainer(ID id, T maxCapacity, T currentVolume) {
        super(id);
        this.maxCapacity = maxCapacity;
        this.currentVolume = currentVolume;
    }

    public T getCurrentVolume() {
        return currentVolume;
    }

    public void setCurrentVolume(T currentVolume) {
        this.currentVolume = currentVolume;
    }

    public T getMaxCapacity() {
        return maxCapacity;
    }

    public void fill(T quantity) throws WaterOverFlowException {
        if (
                quantity.add(this.currentVolume).getValue() > this.maxCapacity.getValue()
        ) {
            throw new WaterOverFlowException("water is overflowing");
        }

        this.currentVolume = quantity;
    }

    public void empty() {
        this.currentVolume.empty();
    }
}
