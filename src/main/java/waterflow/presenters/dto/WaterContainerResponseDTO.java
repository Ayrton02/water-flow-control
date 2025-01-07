package waterflow.presenters.dto;

public record WaterContainerResponseDTO(
    String id,
    Double maxCapacity,
    Double currentCapacity,
    String type,
    String createdAt,
    String updatedAt
) { }
