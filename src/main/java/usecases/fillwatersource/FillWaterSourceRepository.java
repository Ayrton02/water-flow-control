package usecases.fillwatersource;

import domain.entities.WaterSource;
import domain.valueobjects.ID;

public interface FillWaterSourceRepository {
    WaterSource findWaterSourceById(ID id);
}
