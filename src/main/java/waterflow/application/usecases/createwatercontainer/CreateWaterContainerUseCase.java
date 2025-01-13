package waterflow.application.usecases.createwatercontainer;

import infra.logger.Logger;
import waterflow.domain.entities.WaterContainer;
import waterflow.domain.factories.WaterContainerFactory;

public class CreateWaterContainerUseCase implements ICreateWaterContainerUseCase {
    private final Logger logger;
    private final CreateWaterContainerRepository repository;

    public CreateWaterContainerUseCase( Logger logger, CreateWaterContainerRepository repository) {
        this.logger = logger;
        this.repository = repository;
    }

    @Override
    public CreateWaterContainerOutput execute(CreateWaterContainerInput input) {
        this.logger.info("Creating water container %s", input);
        WaterContainer container = WaterContainerFactory.createWaterContainer(
                input.getMaxCapacity(),
                input.getCurrentCapacity(),
                input.getType()
        );

        repository.save(container);
        this.logger.info("Water container created %s", container);
        return CreateWaterContainerOutput.with(container);
    }
}
