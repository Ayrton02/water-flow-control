package waterflow.domain.entities;

import core.valueobjects.ID;
import waterflow.domain.enums.VolumeType;
import waterflow.domain.valueobjects.Liter;

public class LiterWaterContainer extends WaterContainer<Liter>{
    public LiterWaterContainer(Liter maxCapacity, Liter currentCapacity) {
        super(maxCapacity, currentCapacity);
    }

    public LiterWaterContainer(ID id, Liter maxCapacity, Liter currentCapacity) {
        super(id, maxCapacity, currentCapacity);
    }

    @Override
    public VolumeType getVolumeType() {
        return VolumeType.LITER;
    }

    @Override
    public String toString() {
        return String.format(
            "LiterWaterContainer[id=%s, maxCapacity=%s, currentCapacity=%s, createdAt=%s, updatedAt=%s]",
            this.getId().toString(),
            this.getMaxCapacity().toString(),
            this.getCurrentVolume().toString(),
            this.getCreatedAt().toString(),
            this.getUpdatedAt().toString()
        );
    }
}
