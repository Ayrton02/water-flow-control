package waterflow.domain.entities;

import core.valueobjects.ID;
import waterflow.domain.enums.VolumeType;
import waterflow.domain.valueobjects.Liter;
import waterflow.domain.valueobjects.VolumeFlow;

public class LiterWaterPump extends WaterPump<Liter> {

    public LiterWaterPump(
            ID id,
            VolumeFlow<Liter> volumeFlow,
            boolean isActive
    ) {
        super(id, volumeFlow, isActive);
    }

    public LiterWaterPump(
            VolumeFlow<Liter> volumeFlow,
            boolean isActive
    ) {
        super(volumeFlow, isActive);
    }

    @Override
    public VolumeType getVolumeType() {
        return VolumeType.LITER;
    }

    @Override
    public String toString() {
        return String.format(
            "LiterWaterPump[id=%s, volumeFlow=%s, isActive=%b, createdAt=%s, updatedAt=%s]",
            this.getId().toString(),
            this.getVolumeFlow().toString(),
            this.isActive(),
            this.getCreatedAt().toString(),
            this.getUpdatedAt().toString()
        );
    }
}
