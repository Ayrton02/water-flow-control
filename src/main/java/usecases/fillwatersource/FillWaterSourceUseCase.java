package usecases.fillwatersource;

import domain.entities.WaterSource;
import domain.valueobjects.ID;

public class FillWaterSourceUseCase {

    private final FillWaterSourceRepository repository;

    public FillWaterSourceUseCase(FillWaterSourceRepository repository) {
        this.repository = repository;
    }
    public void fillWaterSource(ID waterSourceId) {
        WaterSource waterSource = this.repository.findWaterSourceById(waterSourceId);

        if (waterSource == null) {
            throw new WaterSourceNotFoundException();
        }
    }
}
