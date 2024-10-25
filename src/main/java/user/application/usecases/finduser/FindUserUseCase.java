package user.application.usecases.finduser;

import user.domain.entities.User;
import user.domain.exceptions.UserNotFoundException;

public class FindUserUseCase implements IFindUserUseCase {
    private final FindUserUseCaseRepository repository;

    public FindUserUseCase(FindUserUseCaseRepository repository) {
        this.repository = repository;
    }

    public FindUserOutput execute(FindUserInput input) {
        User user = this.repository.findUser(input.getId());

        if (user == null) {
            throw new UserNotFoundException();
        }

        return FindUserOutput.from(user);
    }
}
