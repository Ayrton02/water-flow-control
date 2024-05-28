package entities;

import core.Entity;
import domain.valueobjects.ID;
import domain.valueobjects.Volume;

public class WaterContainer extends Entity<WaterContainer> {
    private final Volume maxCapacity;
    private Volume currentCapacity;

    public WaterContainer(ID id, WaterContainer data) {
        super(id, data);
        this.maxCapacity = new Volume(1d,23d,4.0);
    }
}
