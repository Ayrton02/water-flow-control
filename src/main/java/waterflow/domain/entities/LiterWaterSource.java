package waterflow.domain.entities;

import core.valueobjects.ID;
import waterflow.domain.enums.VolumeType;
import waterflow.domain.valueobjects.Liter;

public class LiterWaterSource extends WaterSource<Liter> {

    public LiterWaterSource(Liter maxCapacity, Liter safetyThreshold, Liter currentVolume) {
        super(maxCapacity, safetyThreshold, currentVolume);
    }

    public LiterWaterSource(ID id, Liter maxCapacity, Liter safetyThreshold, Liter currentVolume) {
        super(id, maxCapacity, safetyThreshold, currentVolume);
    }

    @Override
    public VolumeType getVolumeType() {
        return VolumeType.LITER;
    }

    @Override
    public String toString() {
        return String.format(
            "LiterWaterSource[id=%s, maxCapacity=%s, safetyThreshold=%s, currentVolume=%s, createdAt=%s, updatedAt=%s]",
            this.getId().toString(),
            this.getMaxCapacity().toString(),
            this.getSafetyThreshold().toString(),
            this.getCurrentVolume().toString(),
            this.getCreatedAt().toString(),
            this.getUpdatedAt().toString()
        );
    }
}
