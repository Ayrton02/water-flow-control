package waterflow.presenters.mappers;

import waterflow.application.usecases.createwatercontainer.CreateWaterContainerOutput;
import waterflow.presenters.dto.WaterContainerResponseDTO;

public class WaterContainerMapper {
  public static WaterContainerResponseDTO toResponseDTO(CreateWaterContainerOutput output) {
    return new WaterContainerResponseDTO(
        output.getId(),
        output.getMaxCapacity(),
        output.getCurrentCapacity(),
        output.getType(),
        output.getCreatedAt(),
        output.getUpdatedAt()
    );
  }
}
