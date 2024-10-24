package user.application.usecases.createuser;

import user.domain.entities.User;

public interface CreateUserRepository {
    void save(User user);
}
