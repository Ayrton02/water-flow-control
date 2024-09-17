package domain.entities;

import domain.valueobjects.ID;
import domain.valueobjects.Liter;

public class LiterWaterContainer extends WaterContainer<Liter>{
    public LiterWaterContainer(Liter maxCapacity, Liter currentCapacity) {
        super(maxCapacity, currentCapacity);
    }

    public LiterWaterContainer(ID id, Liter maxCapacity, Liter currentCapacity) {
        super(id, maxCapacity, currentCapacity);
    }
}
