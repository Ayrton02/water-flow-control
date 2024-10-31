package waterflow.application.usecases.createwatercontainer;

import waterflow.domain.entities.WaterContainer;
import waterflow.domain.factories.WaterContainerFactory;

public class CreateWaterContainerUseCase implements ICreateWaterContainerUseCase {
    private final CreateWaterContainerRepository repository;

    public CreateWaterContainerUseCase(CreateWaterContainerRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreateWaterContainerOutput execute(CreateWaterContainerInput input) {
        WaterContainer container = WaterContainerFactory.createWaterContainer(
                input.getMaxCapacity(),
                input.getCurrentCapacity(),
                input.getType()
        );

        repository.save(container);

        return CreateWaterContainerOutput.with(container);
    }
}
