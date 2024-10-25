package user.application.usecases.finduser;

import core.valueobjects.ID;
import user.domain.entities.User;

public interface FindUserUseCaseRepository {
    User findUser(ID id);
}
