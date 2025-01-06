package waterflow.presenters.mappers;

import waterflow.application.usecases.completewaterflowsession.CompleteWaterFlowSessionOutput;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionOutput;
import waterflow.application.usecases.startwaterflowsession.StartWaterFlowSessionOutput;
import waterflow.presenters.dto.WaterFlowSessionResponseDTO;

public class WaterFlowSessionMapper {

    public static WaterFlowSessionResponseDTO toResponseDTO(CreateWaterFlowSessionOutput output) {
        return new WaterFlowSessionResponseDTO(
            output.getId(),
            output.getCreatedAt(),
            null,
            null,
            output.getStatus(),
            output.getUserId()
        );
    }

  public static WaterFlowSessionResponseDTO toResponseDTO(StartWaterFlowSessionOutput output) {
      return new WaterFlowSessionResponseDTO(
          output.getId(),
          output.getCreatedAt(),
          output.getStartedAt(),
          null,
          output.getStatus(),
          output.getUserId()
      );
  }

  public static WaterFlowSessionResponseDTO toResponseDTO(CompleteWaterFlowSessionOutput output) {
      return new WaterFlowSessionResponseDTO(
          output.getId(),
          output.getCreatedAt(),
          output.getStartedAt(),
          output.getFinishedAt(),
          output.getStatus(),
          output.getUserId()
      );
  }
}
