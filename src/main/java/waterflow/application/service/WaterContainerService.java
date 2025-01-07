package waterflow.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import waterflow.application.usecases.createwatercontainer.CreateWaterContainerInput;
import waterflow.application.usecases.createwatercontainer.ICreateWaterContainerUseCase;
import waterflow.domain.enums.VolumeType;
import waterflow.presenters.dto.WaterContainerRequestDTO;
import waterflow.presenters.dto.WaterContainerResponseDTO;
import waterflow.presenters.mappers.WaterContainerMapper;

@ApplicationScoped
public class WaterContainerService {
  private final ICreateWaterContainerUseCase createWaterContainerUseCase;

  @Inject
  public WaterContainerService(ICreateWaterContainerUseCase createWaterContainerUseCase) {
    this.createWaterContainerUseCase = createWaterContainerUseCase;
  }

  public WaterContainerResponseDTO createContainer(WaterContainerRequestDTO request) {
    return WaterContainerMapper.toResponseDTO(
      this.createWaterContainerUseCase.execute(
          CreateWaterContainerInput.with(
              request.maxCapacity(),
              request.currentCapacity(),
              VolumeType.fromString(request.type())
          )
      )
    );
  }
}
