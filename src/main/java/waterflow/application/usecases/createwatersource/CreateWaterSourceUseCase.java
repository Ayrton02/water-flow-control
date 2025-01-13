package waterflow.application.usecases.createwatersource;

import infra.logger.Logger;
import waterflow.domain.entities.WaterSource;
import waterflow.domain.factories.WaterSourceFactory;

public class CreateWaterSourceUseCase implements ICreateWaterSourceUseCase {
    private final Logger logger;
    private final CreateWaterSourceRepository repository;

    public CreateWaterSourceUseCase(Logger logger, CreateWaterSourceRepository repository) {
        this.logger = logger;
        this.repository = repository;
    }

    @Override
    public CreateWaterSourceOutput execute(CreateWaterSourceInput input) {
        this.logger.info("Creating water source %s", input);
        WaterSource source = WaterSourceFactory.createWaterSource(
                input.getMaxCapacity(),
                input.getSafetyThreshold(),
                input.getCurrentCapacity(),
                input.getType()
        );

        repository.save(source);
        this.logger.info("Water source created %s", source);
        return CreateWaterSourceOutput.with(source);
    }
}
