package waterflow.configuration;

import infra.logger.LoggerImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import user.application.usecases.finduser.FindUserRepository;
import waterflow.application.usecases.completewaterflowsession.CompleteWaterFlowSessionRepository;
import waterflow.application.usecases.completewaterflowsession.CompleteWaterFlowSessionUseCase;
import waterflow.application.usecases.completewaterflowsession.ICompleteWaterFlowSessionUseCase;
import waterflow.application.usecases.createwatercontainer.CreateWaterContainerRepository;
import waterflow.application.usecases.createwatercontainer.CreateWaterContainerUseCase;
import waterflow.application.usecases.createwatercontainer.ICreateWaterContainerUseCase;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionRepository;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionUseCase;
import waterflow.application.usecases.createwaterflowsession.ICreateWaterFlowSessionUseCase;
import waterflow.application.usecases.createwaterpump.CreateWaterPumpRepository;
import waterflow.application.usecases.createwaterpump.CreateWaterPumpUseCase;
import waterflow.application.usecases.createwaterpump.ICreateWaterPumpUseCase;
import waterflow.application.usecases.createwatersource.CreateWaterSourceRepository;
import waterflow.application.usecases.createwatersource.CreateWaterSourceUseCase;
import waterflow.application.usecases.createwatersource.ICreateWaterSourceUseCase;
import waterflow.application.usecases.findwaterflowsession.FindWaterFlowSessionRepository;
import waterflow.application.usecases.findwaterflowsession.FindWaterFlowSessionUseCase;
import waterflow.application.usecases.findwaterflowsession.IFindWaterFlowSessionUseCase;
import waterflow.application.usecases.startwaterflowsession.IStartWaterFlowSessionUseCase;
import waterflow.application.usecases.startwaterflowsession.StartWaterFlowSessionRepository;
import waterflow.application.usecases.startwaterflowsession.StartWaterFlowSessionUseCase;
import waterflow.application.usecases.syncpreviewwaterflowsession.ISyncPreviewWaterFlowSessionUseCase;
import waterflow.application.usecases.syncpreviewwaterflowsession.SyncPreviewWaterFlowSessionRepository;
import waterflow.application.usecases.syncpreviewwaterflowsession.SyncPreviewWaterFlowSessionUseCase;
import waterflow.application.usecases.syncwaterflowsession.ISyncWaterFlowSessionUseCase;
import waterflow.application.usecases.syncwaterflowsession.SyncWaterFlowSessionRepository;
import waterflow.application.usecases.syncwaterflowsession.SyncWaterFlowSessionUseCase;

@ApplicationScoped
public class UseCaseConfig {

  @Produces
  public ICreateWaterFlowSessionUseCase createWaterFlowSessionUseCase(
      CreateWaterFlowSessionRepository repository,
      FindUserRepository findUserRepository
  ) {
    return new CreateWaterFlowSessionUseCase(
        new LoggerImpl(CreateWaterFlowSessionUseCase.class),
        repository,
        findUserRepository
    );
  }

  @Produces
  public IStartWaterFlowSessionUseCase startWaterFlowSessionUseCase(StartWaterFlowSessionRepository repository) {
    return new StartWaterFlowSessionUseCase(new LoggerImpl(StartWaterFlowSessionUseCase.class), repository);
  }

  @Produces
  public ICompleteWaterFlowSessionUseCase completeWaterFlowSessionUseCase(CompleteWaterFlowSessionRepository repository) {
    return new CompleteWaterFlowSessionUseCase(new LoggerImpl(CompleteWaterFlowSessionUseCase.class), repository);
  }

  @Produces
  public ISyncWaterFlowSessionUseCase syncWaterFlowSessionUseCase(SyncWaterFlowSessionRepository repository) {
    return new SyncWaterFlowSessionUseCase(new LoggerImpl(SyncWaterFlowSessionUseCase.class), repository);
  }

  @Produces
  public ICreateWaterPumpUseCase createWaterPumpUseCase(CreateWaterPumpRepository repository) {
    return new CreateWaterPumpUseCase(new LoggerImpl(CreateWaterPumpUseCase.class), repository);
  }

  @Produces
  public ICreateWaterContainerUseCase createWaterContainerUseCase(CreateWaterContainerRepository repository) {
    return new CreateWaterContainerUseCase(new LoggerImpl(CreateWaterContainerUseCase.class), repository);
  }

  @Produces
  public ICreateWaterSourceUseCase createWaterSourceUseCase(CreateWaterSourceRepository repository) {
    return new CreateWaterSourceUseCase(new LoggerImpl(CreateWaterSourceUseCase.class), repository);
  }

  @Produces
  public IFindWaterFlowSessionUseCase findWaterFlowSessionUseCase(FindWaterFlowSessionRepository repository) {
    return new FindWaterFlowSessionUseCase(new LoggerImpl(FindWaterFlowSessionUseCase.class), repository);
  }

  @Produces
  public ISyncPreviewWaterFlowSessionUseCase syncPreviewWaterFlowSessionUseCase(SyncPreviewWaterFlowSessionRepository repository) {
    return new SyncPreviewWaterFlowSessionUseCase(new LoggerImpl(SyncPreviewWaterFlowSessionUseCase.class), repository);
  }
}
