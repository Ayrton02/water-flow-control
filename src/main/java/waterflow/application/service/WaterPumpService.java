package waterflow.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import waterflow.application.usecases.createwaterpump.CreateWaterPumpInput;
import waterflow.application.usecases.createwaterpump.ICreateWaterPumpUseCase;
import waterflow.domain.enums.TimeMeasurementUnit;
import waterflow.domain.enums.VolumeType;
import waterflow.presenters.dto.WaterPumpRequestDTO;
import waterflow.presenters.dto.WaterPumpResponseDTO;
import waterflow.presenters.mappers.WaterPumpMapper;

@ApplicationScoped
public class WaterPumpService {
  private final ICreateWaterPumpUseCase createWaterPumpUseCase;

  @Inject
  public WaterPumpService(ICreateWaterPumpUseCase createWaterPumpUseCase) {
    this.createWaterPumpUseCase = createWaterPumpUseCase;
  }

  public WaterPumpResponseDTO createPump(WaterPumpRequestDTO request) {
    return WaterPumpMapper.toResponseDTO(
      this.createWaterPumpUseCase.execute(
          CreateWaterPumpInput.with(
              request.volume(),
              TimeMeasurementUnit.fromString(request.unit()),
              VolumeType.fromString(request.type())
          )
      )
    );
  }
}
