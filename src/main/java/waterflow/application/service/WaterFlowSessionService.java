package waterflow.application.service;

import core.valueobjects.DateTime;
import core.valueobjects.ID;
import core.valueobjects.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import waterflow.application.usecases.completewaterflowsession.CompleteWaterFlowSessionInput;
import waterflow.application.usecases.completewaterflowsession.ICompleteWaterFlowSessionUseCase;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionInput;
import waterflow.application.usecases.createwaterflowsession.ICreateWaterFlowSessionUseCase;
import waterflow.application.usecases.findwaterflowsession.FindWaterFlowSessionInput;
import waterflow.application.usecases.findwaterflowsession.IFindWaterFlowSessionUseCase;
import waterflow.application.usecases.startwaterflowsession.IStartWaterFlowSessionUseCase;
import waterflow.application.usecases.startwaterflowsession.StartWaterFlowSessionInput;
import waterflow.application.usecases.syncpreviewwaterflowsession.ISyncPreviewWaterFlowSessionUseCase;
import waterflow.application.usecases.syncpreviewwaterflowsession.SyncPreviewWaterFlowSessionInput;
import waterflow.application.usecases.syncwaterflowsession.ISyncWaterFlowSessionUseCase;
import waterflow.application.usecases.syncwaterflowsession.SyncWaterFlowSessionInput;
import waterflow.infra.panache.repositories.PanacheWaterFlowSessionRepository;
import waterflow.presenters.dto.*;
import waterflow.presenters.mappers.WaterFlowSessionMapper;

import java.util.List;

@ApplicationScoped
public class WaterFlowSessionService {
    private final ICreateWaterFlowSessionUseCase createWaterFlowSessionUseCase;
    private final IStartWaterFlowSessionUseCase startWaterFlowSessionUseCase;
    private final ICompleteWaterFlowSessionUseCase completeWaterFlowSessionUseCase;
    private final ISyncWaterFlowSessionUseCase syncWaterFlowSessionUseCase;
    private final IFindWaterFlowSessionUseCase findWaterFlowSessionUseCase;
    private final ISyncPreviewWaterFlowSessionUseCase syncPreviewWaterFlowSessionUseCase;
    private final PanacheWaterFlowSessionRepository repository;

    @Inject
    public WaterFlowSessionService(
        ICreateWaterFlowSessionUseCase createWaterFlowSessionUseCase,
        IStartWaterFlowSessionUseCase startWaterFlowSessionUseCase,
        ICompleteWaterFlowSessionUseCase completeWaterFlowSessionUseCase,
        ISyncWaterFlowSessionUseCase syncWaterFlowSessionUseCase,
        IFindWaterFlowSessionUseCase findWaterFlowSessionUseCase,
        ISyncPreviewWaterFlowSessionUseCase syncPreviewWaterFlowSessionUseCase,
        PanacheWaterFlowSessionRepository repository
    ) {
        this.createWaterFlowSessionUseCase = createWaterFlowSessionUseCase;
        this.startWaterFlowSessionUseCase = startWaterFlowSessionUseCase;
        this.completeWaterFlowSessionUseCase = completeWaterFlowSessionUseCase;
        this.syncWaterFlowSessionUseCase = syncWaterFlowSessionUseCase;
        this.findWaterFlowSessionUseCase = findWaterFlowSessionUseCase;
        this.syncPreviewWaterFlowSessionUseCase = syncPreviewWaterFlowSessionUseCase;
        this.repository = repository;
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

    public WaterFlowSessionResponseDTO completeSession(String id) {
        return WaterFlowSessionMapper.toResponseDTO(
            this.completeWaterFlowSessionUseCase.execute(
                CompleteWaterFlowSessionInput.with(
                    UUID.from(id)
                )
            )
        );
    }

    public SyncWaterFlowSessionResponseDTO syncSession(String id) {
        return WaterFlowSessionMapper.toResponseDTO(
            this.syncWaterFlowSessionUseCase.execute(
                SyncWaterFlowSessionInput.with(
                    UUID.from(id)
                )
            )
        );
    }

    public FindWaterFlowSessionResponseDTO findSession(String id) {
        return WaterFlowSessionMapper.toResponseDTO(
            this.findWaterFlowSessionUseCase.execute(
                FindWaterFlowSessionInput.with(
                    UUID.from(id)
                )
            )
        );
    }

    public List<ID> findActiveIds() {
        return this.repository.findActiveIds();
    }

    public SyncPreviewWaterFlowSessionResponseDTO syncPreviewSession(SyncPreviewWaterFlowSessionRequestDTO request) {
        return WaterFlowSessionMapper.toResponseDTO(
            this.syncPreviewWaterFlowSessionUseCase.execute(
                new SyncPreviewWaterFlowSessionInput(
                    UUID.from(request.id()),
                    DateTime.parse(request.startDate()),
                    DateTime.parse(request.endDate())
                )
            )
        );
    }
}
