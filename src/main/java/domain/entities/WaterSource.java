package domain.entities;

import core.BaseEntity;
import domain.exceptions.NegativeVolumeException;
import domain.exceptions.SafetyThresholdException;
import domain.valueobjects.Volume;
import domain.valueobjects.ID;
import domain.valueobjects.UUID;

public abstract class WaterSource<T extends Volume<T>> extends BaseEntity {
    private final T maxCapacity;
    private final T safetyThreshold;
    private T currentVolume;

    WaterSource(T maxCapacity, T safetyThreshold, T currentVolume) {
        super(UUID.generate());
        this.maxCapacity = maxCapacity;
        this.safetyThreshold = safetyThreshold;
        this.currentVolume = currentVolume;
    }

    WaterSource(ID id, T maxCapacity, T safetyThreshold, T currentVolume) {
        super(id);
        this.maxCapacity = maxCapacity;
        this.safetyThreshold = safetyThreshold;
        this.currentVolume = currentVolume;
    }

    public T getCurrentVolume() {
        return currentVolume;
    }

    public T getSafetyThreshold() {
        return safetyThreshold;
    }

    public T dump(T volume) throws SafetyThresholdException, NegativeVolumeException {
        if (this.currentVolume.compareTo(this.safetyThreshold) < 0) {
            throw new SafetyThresholdException("safety threshold reached, try again later");
        }

        this.currentVolume.subtract(volume);
        return volume;
    }
}
