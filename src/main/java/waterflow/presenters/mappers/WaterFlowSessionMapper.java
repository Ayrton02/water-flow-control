package waterflow.presenters.mappers;

import waterflow.application.usecases.completewaterflowsession.CompleteWaterFlowSessionOutput;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionOutput;
import waterflow.application.usecases.findwaterflowsession.FindWaterFlowSessionOutput;
import waterflow.application.usecases.startwaterflowsession.StartWaterFlowSessionOutput;
import waterflow.application.usecases.syncpreviewwaterflowsession.SyncPreviewWaterFlowSessionOutput;
import waterflow.application.usecases.syncwaterflowsession.SyncWaterFlowSessionOutput;
import waterflow.presenters.dto.FindWaterFlowSessionResponseDTO;
import waterflow.presenters.dto.SyncPreviewWaterFlowSessionResponseDTO;
import waterflow.presenters.dto.SyncWaterFlowSessionResponseDTO;
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

  public static SyncWaterFlowSessionResponseDTO toResponseDTO(SyncWaterFlowSessionOutput output) {
    return new SyncWaterFlowSessionResponseDTO(
        output.getId(),
        output.getCreatedAt(),
        output.getStartedAt(),
        output.getStatus(),
        output.getContainerVolume(),
        output.getSourceVolume(),
        output.getUserId()
    );
  }

  public static FindWaterFlowSessionResponseDTO toResponseDTO(FindWaterFlowSessionOutput output) {
    return new FindWaterFlowSessionResponseDTO(
        output.getId(),
        output.getStartedAt(),
        output.getFinishedAt(),
        output.getStatus(),
        output.getContainerId(),
        output.getSourceId(),
        output.getPumpId(),
        output.getUserId(),
        output.getCreatedAt(),
        output.getUpdatedAt()
    );
  }

  public static SyncPreviewWaterFlowSessionResponseDTO toResponseDTO(SyncPreviewWaterFlowSessionOutput output) {
      return new SyncPreviewWaterFlowSessionResponseDTO(
          output.id(),
          output.status(),
          output.containerVolume(),
          output.containerMaxCapacity(),
          output.sourceVolume(),
          output.sourceSafetyThreshold()
      );
  }
}
