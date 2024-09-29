package domain.entities;

import domain.valueobjects.ID;
import domain.valueobjects.Liter;
import domain.valueobjects.VolumeFlow;

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
}
