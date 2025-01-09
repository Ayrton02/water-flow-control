package waterflow.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import waterflow.application.usecases.createwatersource.CreateWaterSourceInput;
import waterflow.application.usecases.createwatersource.ICreateWaterSourceUseCase;
import waterflow.domain.enums.VolumeType;
import waterflow.presenters.dto.WaterSourceRequestDTO;
import waterflow.presenters.dto.WaterSourceResponseDTO;
import waterflow.presenters.mappers.WaterSourceMapper;

@ApplicationScoped
public class WaterSourceService {
  private final ICreateWaterSourceUseCase createWaterSourceUseCase;

  @Inject
  public WaterSourceService(ICreateWaterSourceUseCase createWaterSourceUseCase) {
    this.createWaterSourceUseCase = createWaterSourceUseCase;
  }

  public WaterSourceResponseDTO createSource(WaterSourceRequestDTO request) {
    return WaterSourceMapper.toResponseDTO(
        this.createWaterSourceUseCase.execute(
            CreateWaterSourceInput.with(
                request.maxCapacity(),
                request.safetyThreshold(),
                request.currentCapacity(),
                VolumeType.valueOf(request.type().toUpperCase())
            )
        )
    );
  }
}
