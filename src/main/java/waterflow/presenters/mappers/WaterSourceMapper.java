package waterflow.presenters.mappers;

import waterflow.application.usecases.createwatersource.CreateWaterSourceOutput;
import waterflow.presenters.dto.WaterSourceResponseDTO;

public class WaterSourceMapper {
  public static WaterSourceResponseDTO toResponseDTO(CreateWaterSourceOutput output) {
    return new WaterSourceResponseDTO(
        output.getId(),
        output.getMaxCapacity(),
        output.getSafetyThreshold(),
        output.getCurrentCapacity(),
        output.getType(),
        output.getCreatedAt(),
        output.getUpdatedAt()
    );
  }
}
