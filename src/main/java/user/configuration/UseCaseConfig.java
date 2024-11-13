package user.configuration;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import user.application.usecases.createuser.CreateUserRepository;
import user.application.usecases.createuser.CreateUserUseCase;
import user.application.usecases.createuser.ICreateUserUseCase;
import user.application.usecases.finduser.FindUserRepository;
import user.application.usecases.finduser.FindUserUseCase;
import user.application.usecases.finduser.IFindUserUseCase;

@ApplicationScoped
public class UseCaseConfig {

    @Produces
    public ICreateUserUseCase createUserUseCase(CreateUserRepository repository) {
        return new CreateUserUseCase(repository);
    }

    @Produces
    public IFindUserUseCase findUserUseCase(FindUserRepository repository) {
        return new FindUserUseCase(repository);
    }
}
