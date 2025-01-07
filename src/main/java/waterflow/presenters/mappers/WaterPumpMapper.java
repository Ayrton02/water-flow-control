package waterflow.presenters.mappers;

import waterflow.application.usecases.createwaterpump.CreateWaterPumpOutput;
import waterflow.presenters.dto.WaterPumpResponseDTO;

public class WaterPumpMapper {
  public static WaterPumpResponseDTO toResponseDTO(CreateWaterPumpOutput output) {
    return new WaterPumpResponseDTO(
        output.getId(),
        output.getFlow(),
        output.getType(),
        output.getActive(),
        output.getCreatedAt(),
        output.getUpdatedAt()
    );
  }
}
