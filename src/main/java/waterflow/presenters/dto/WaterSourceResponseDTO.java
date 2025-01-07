package waterflow.presenters.dto;

public record WaterSourceResponseDTO(
    String id,
    Double maxCapacity,
    Double safetyThreshold,
    Double currentCapacity,
    String type,
    String createdAt,
    String updatedAt
) { }
