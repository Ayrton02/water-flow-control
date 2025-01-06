package waterflow.application.service;

import core.valueobjects.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionInput;
import waterflow.application.usecases.createwaterflowsession.ICreateWaterFlowSessionUseCase;
import waterflow.application.usecases.startwaterflowsession.IStartWaterFlowSessionUseCase;
import waterflow.application.usecases.startwaterflowsession.StartWaterFlowSessionInput;
import waterflow.presenters.dto.WaterFlowSessionRequestDTO;
import waterflow.presenters.dto.WaterFlowSessionResponseDTO;
import waterflow.presenters.mappers.WaterFlowSessionMapper;

@ApplicationScoped
public class WaterFlowSessionService {
    private final ICreateWaterFlowSessionUseCase createWaterFlowSessionUseCase;
    private final IStartWaterFlowSessionUseCase startWaterFlowSessionUseCase;

    @Inject
    public WaterFlowSessionService(
        ICreateWaterFlowSessionUseCase createWaterFlowSessionUseCase,
        IStartWaterFlowSessionUseCase startWaterFlowSessionUseCase
    ) {
        this.createWaterFlowSessionUseCase = createWaterFlowSessionUseCase;
        this.startWaterFlowSessionUseCase = startWaterFlowSessionUseCase;
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

    public WaterFlowSessionResponseDTO startSession(String id) {
        return WaterFlowSessionMapper.toResponseDTO(
            this.startWaterFlowSessionUseCase.execute(
                StartWaterFlowSessionInput.with(
                    UUID.from(id)
                )
            )
        );
    }
}
