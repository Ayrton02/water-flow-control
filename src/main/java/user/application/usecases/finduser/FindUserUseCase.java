package user.application.usecases.finduser;

import infra.logger.Logger;
import user.domain.entities.User;
import user.domain.exceptions.UserNotFoundException;

public class FindUserUseCase implements IFindUserUseCase {
    private final Logger logger;
    private final FindUserRepository repository;

    public FindUserUseCase(Logger logger, FindUserRepository repository) {
        this.logger = logger;
        this.repository = repository;
    }

    public FindUserOutput execute(FindUserInput input) {
        this.logger.info("Searching for user %s", input);
        User user = this.repository.findUser(input.id());

        if (user == null) {
            this.logger.warn("User was not found %s", input);
            throw new UserNotFoundException();
        }

        return FindUserOutput.from(user);
    }
}
