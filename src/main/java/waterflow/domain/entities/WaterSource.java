package waterflow.domain.entities;

import core.baseclasses.BaseEntity;
import waterflow.domain.exceptions.NegativeVolumeException;
import waterflow.domain.exceptions.SafetyThresholdException;
import core.valueobjects.ID;
import core.valueobjects.UUID;
import waterflow.domain.valueobjects.Volume;
import waterflow.domain.valueobjects.WaterComponent;

public abstract class WaterSource<T extends Volume<T>> extends BaseEntity implements WaterComponent {
    private final T maxCapacity;
    private final T safetyThreshold;
    private final T currentVolume;

    protected WaterSource(T maxCapacity, T safetyThreshold, T currentVolume) {
        super(UUID.generate());
        this.maxCapacity = maxCapacity;
        this.safetyThreshold = safetyThreshold;
        this.currentVolume = currentVolume;
    }

    protected WaterSource(ID id, T maxCapacity, T safetyThreshold, T currentVolume) {
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

    public T getMaxCapacity() {
        return maxCapacity;
    }

    public T dump(T volume) throws SafetyThresholdException, NegativeVolumeException {
        if (this.currentVolume.compareTo(this.safetyThreshold) < 0) {
            throw new SafetyThresholdException("safety threshold reached, try again later");
        }

        this.currentVolume.subtract(volume);
        return volume;
    }
}
