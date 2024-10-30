package waterflow.application.usecases.createwaterpump;

import waterflow.domain.entities.WaterPump;
import waterflow.domain.factories.WaterPumpFactory;

public class CreateWaterPumpUseCase implements ICreateWaterPumpUseCase {
    private final CreateWaterPumpRepository repository;

    public CreateWaterPumpUseCase(CreateWaterPumpRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreateWaterPumpOutput execute(CreateWaterPumpInput input) {
        WaterPump pump = WaterPumpFactory.createWaterPump(
                input.getVolume(),
                input.getTimeUnit(),
                input.getType()
        );

        repository.save(pump);

        return CreateWaterPumpOutput.with(pump);
    }
}
