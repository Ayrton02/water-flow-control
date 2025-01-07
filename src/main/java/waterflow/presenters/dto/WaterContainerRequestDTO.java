package waterflow.presenters.dto;

public record WaterContainerRequestDTO (Double maxCapacity, Double currentCapacity, String type) { }
