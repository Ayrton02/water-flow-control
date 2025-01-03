package user.application.usecases.createuser;

import user.domain.entities.User;

public class CreateUserUseCase implements ICreateUserUseCase {
    private final CreateUserRepository repository;

    public CreateUserUseCase(CreateUserRepository repository) {
        this.repository = repository;
    }

    public CreateUserOutput execute(CreateUserInput input) {
        User user = new User(
                input.name(),
                input.documentNumber()
        );

        this.repository.save(user);
        return CreateUserOutput.from(user);
    }
}
