package user.application.usecases.finduser;

import user.domain.entities.User;
import user.domain.exceptions.UserNotFoundException;

public class FindUserUseCase implements IFindUserUseCase {
    private final FindUserRepository repository;

    public FindUserUseCase(FindUserRepository repository) {
        this.repository = repository;
    }

    public FindUserOutput execute(FindUserInput input) {
        User user = this.repository.findUser(input.id());

        if (user == null) {
            throw new UserNotFoundException();
        }

        return FindUserOutput.from(user);
    }
}
