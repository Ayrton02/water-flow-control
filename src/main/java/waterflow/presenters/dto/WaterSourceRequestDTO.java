package waterflow.presenters.dto;

public record WaterSourceRequestDTO(
    Double maxCapacity,
    Double safetyThreshold,
    Double currentCapacity,
    String type
) { }
