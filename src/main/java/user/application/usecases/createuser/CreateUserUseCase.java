package user.application.usecases.createuser;

import infra.logger.Logger;
import user.domain.entities.User;

public class CreateUserUseCase implements ICreateUserUseCase {
    private final Logger logger;
    private final CreateUserRepository repository;

    public CreateUserUseCase(Logger logger, CreateUserRepository repository) {
        this.logger = logger;
        this.repository = repository;
    }

    public CreateUserOutput execute(CreateUserInput input) {
        this.logger.info("Creating user %s", input);
        User user = new User(
                input.name(),
                input.documentNumber()
        );

        this.repository.save(user);
        this.logger.info("User created %s", user);
        return CreateUserOutput.from(user);
    }
}
