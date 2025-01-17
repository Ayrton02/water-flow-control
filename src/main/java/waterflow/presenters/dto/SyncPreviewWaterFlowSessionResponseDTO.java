package waterflow.presenters.dto;

public record SyncPreviewWaterFlowSessionResponseDTO(
    String id,
    String status,
    Double containerVolume,
    Double containerMaxCapacity,
    Double sourceVolume,
    Double sourceSafetyThreshold
) { }
