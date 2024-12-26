package waterflow.presenters.mappers;

import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionOutput;
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
}
