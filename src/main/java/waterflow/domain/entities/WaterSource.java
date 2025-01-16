package waterflow.domain.entities;

import core.baseclasses.BaseEntity;
import core.valueobjects.ID;
import core.valueobjects.UUID;
import waterflow.domain.exceptions.NegativeVolumeException;
import waterflow.domain.exceptions.SafetyThresholdException;
import waterflow.domain.valueobjects.Volume;
import waterflow.domain.valueobjects.WaterComponent;

public abstract class WaterSource<T extends Volume<T>> extends BaseEntity implements WaterComponent {
    private final T maxCapacity;
    private final T safetyThreshold;
    private T currentVolume;

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

    public void setCurrentVolume(T currentVolume) {
      this.currentVolume = currentVolume;
    }

    public T dump(T volume) throws SafetyThresholdException, NegativeVolumeException {
        if (this.currentVolume.compareTo(this.safetyThreshold) < 0) {
            throw new SafetyThresholdException("safety threshold reached, try again later");
        }

        this.currentVolume.subtract(volume);
        return volume;
    }

    public void empty() {
        this.currentVolume.subtract(this.currentVolume);
    }
}
