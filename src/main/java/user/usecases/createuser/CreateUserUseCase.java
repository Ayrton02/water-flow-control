package user.usecases.createuser;

import user.domain.entities.User;

public class CreateUserUseCase {

    private final CreateUserRepository repository;

    CreateUserUseCase(CreateUserRepository repository) {
        this.repository = repository;
    }

    public void execute(CreateUserInput input) {
        User user = new User(
                input.getName(),
                input.getDocumentNumber()
        );

        this.repository.save(user);
    }

}
