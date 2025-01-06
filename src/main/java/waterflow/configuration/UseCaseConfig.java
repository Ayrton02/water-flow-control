package waterflow.configuration;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import user.application.usecases.finduser.FindUserRepository;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionRepository;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionUseCase;
import waterflow.application.usecases.createwaterflowsession.ICreateWaterFlowSessionUseCase;
import waterflow.application.usecases.startwaterflowsession.IStartWaterFlowSessionUseCase;
import waterflow.application.usecases.startwaterflowsession.StartWaterFlowSessionRepository;
import waterflow.application.usecases.startwaterflowsession.StartWaterFlowSessionUseCase;

@ApplicationScoped
public class UseCaseConfig {

  @Produces
  public ICreateWaterFlowSessionUseCase createWaterFlowSessionUseCase(
      CreateWaterFlowSessionRepository repository,
      FindUserRepository findUserRepository
  ) {
    return new CreateWaterFlowSessionUseCase(repository, findUserRepository);
  }

  @Produces
  public IStartWaterFlowSessionUseCase startWaterFlowSessionUseCase(StartWaterFlowSessionRepository repository) {
    return new StartWaterFlowSessionUseCase(repository);
  }
}
