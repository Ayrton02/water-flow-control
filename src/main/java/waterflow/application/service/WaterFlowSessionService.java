package waterflow.application.service;

import core.valueobjects.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionInput;
import waterflow.application.usecases.createwaterflowsession.ICreateWaterFlowSessionUseCase;
import waterflow.presenters.dto.WaterFlowSessionRequestDTO;
import waterflow.presenters.dto.WaterFlowSessionResponseDTO;
import waterflow.presenters.mappers.WaterFlowSessionMapper;

@ApplicationScoped
public class WaterFlowSessionService {
    private final ICreateWaterFlowSessionUseCase createWaterFlowSessionUseCase;

    @Inject
    public WaterFlowSessionService(ICreateWaterFlowSessionUseCase createWaterFlowSessionUseCase) {
        this.createWaterFlowSessionUseCase = createWaterFlowSessionUseCase;
    }

    public WaterFlowSessionResponseDTO createSession(WaterFlowSessionRequestDTO request) {
        return WaterFlowSessionMapper.toResponseDTO(
            this.createWaterFlowSessionUseCase.execute(
                CreateWaterFlowSessionInput.with(
                    UUID.from(request.pumpId()),
                    UUID.from(request.containerId()),
                    UUID.from(request.sourceId()),
                    UUID.from(request.userId())
                )
            )
        );
    }
}
