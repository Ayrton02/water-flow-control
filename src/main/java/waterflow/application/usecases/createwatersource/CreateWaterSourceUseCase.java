package waterflow.application.usecases.createwatersource;

import waterflow.domain.entities.WaterSource;
import waterflow.domain.factories.WaterSourceFactory;

public class CreateWaterSourceUseCase implements ICreateWaterSourceUseCase {
    private final CreateWaterSourceRepository repository;

    public CreateWaterSourceUseCase(CreateWaterSourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreateWaterSourceOutput execute(CreateWaterSourceInput input) {
        WaterSource source = WaterSourceFactory.createWaterSource(
                input.getMaxCapacity(),
                input.getSafetyThreshold(),
                input.getCurrentCapacity(),
                input.getType()
        );

        repository.save(source);

        return CreateWaterSourceOutput.with(source);
    }
}
