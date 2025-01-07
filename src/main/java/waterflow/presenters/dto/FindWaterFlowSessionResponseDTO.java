package waterflow.presenters.dto;

public record FindWaterFlowSessionResponseDTO(
    String id,
    String startedAt,
    String finishedAt,
    String status,
    String containerId,
    String sourceId,
    String pumpId,
    String userId,
    String createdAt,
    String updatedAt
) { }
