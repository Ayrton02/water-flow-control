package waterflow.presenters.dto;

public record WaterPumpResponseDTO (
    String id,
    String flow,
    String type,
    Boolean isActive,
    String createdAt,
    String updatedAt
) {}