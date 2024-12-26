package waterflow.presenters.dto;

public record WaterFlowSessionResponseDTO(
    String id,
    String createdAt,
    String startedAt,
    String finishedAt,
    String status,
    String userId
) { }
