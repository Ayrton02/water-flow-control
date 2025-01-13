package waterflow.application.usecases.createwaterpump;

import infra.logger.Logger;
import waterflow.domain.entities.WaterPump;
import waterflow.domain.factories.WaterPumpFactory;

public class CreateWaterPumpUseCase implements ICreateWaterPumpUseCase {
    private final Logger logger;
    private final CreateWaterPumpRepository repository;

    public CreateWaterPumpUseCase(Logger logger, CreateWaterPumpRepository repository) {
        this.logger = logger;
        this.repository = repository;
    }

    @Override
    public CreateWaterPumpOutput execute(CreateWaterPumpInput input) {
        this.logger.info("Creating water pump %s", input);
        WaterPump pump = WaterPumpFactory.createWaterPump(
                input.getVolume(),
                input.getTimeUnit(),
                input.getType()
        );

        repository.save(pump);
        this.logger.info("Water pump created %s", pump);
        return CreateWaterPumpOutput.with(pump);
    }
}
