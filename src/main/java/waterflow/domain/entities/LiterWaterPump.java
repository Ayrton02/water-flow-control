package waterflow.domain.entities;

import core.valueobjects.ID;
import waterflow.domain.enums.VolumeType;
import waterflow.domain.valueobjects.Liter;
import waterflow.domain.valueobjects.VolumeFlow;

public class LiterWaterPump extends WaterPump<Liter> {

    public LiterWaterPump(
            ID id,
            VolumeFlow<Liter> volumeFlow
    ) {
        super(id, volumeFlow);
    }

    public LiterWaterPump(
            VolumeFlow<Liter> volumeFlow
    ) {
        super(volumeFlow);
    }

    @Override
    public VolumeType getVolumeType() {
        return VolumeType.LITER;
    }
}
