package waterflow.presenters.dto;

public record SyncWaterFlowSessionResponseDTO(
    String id,
    String createdAt,
    String startedAt,
    String status,
    Double containerVolume,
    Double sourceVolume,
    String userId
) { }
