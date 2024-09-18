package domain.entities;

import domain.valueobjects.ID;
import domain.valueobjects.Liter;
import domain.valueobjects.WaterFlow;

public class LiterWaterPump extends WaterPump<Liter> {

    protected LiterWaterPump(
            ID id,
            WaterFlow<Liter> waterFlow,
            WaterContainer<Liter> container,
            WaterSource<Liter> source
    ) {
        super(id, waterFlow, container, source);
    }

    protected LiterWaterPump(
            WaterFlow<Liter> waterFlow,
            WaterContainer<Liter> container,
            WaterSource<Liter> source
    ) {
        super(waterFlow, container, source);
    }
}
