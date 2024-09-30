package waterflow.domain.entities;

import core.valueobjects.ID;
import waterflow.domain.valueobjects.Liter;

public class LiterWaterSource extends WaterSource<Liter> {

    public LiterWaterSource(Liter maxCapacity, Liter safetyThreshold, Liter currentVolume) {
        super(maxCapacity, safetyThreshold, currentVolume);
    }

    public LiterWaterSource(ID id, Liter maxCapacity, Liter safetyThreshold, Liter currentVolume) {
        super(id, maxCapacity, safetyThreshold, currentVolume);
    }
}
