package user.configuration;

import core.valueobjects.ID;
import jakarta.enterprise.context.ApplicationScoped;
import user.application.usecases.createuser.CreateUserRepository;
import user.application.usecases.finduser.FindUserRepository;
import user.domain.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RepositoryConfig implements FindUserRepository, CreateUserRepository {
    private List<User> users = new ArrayList<>();
    @Override
    public void save(User user) {
        this.users.add(user);
    }

    @Override
    public User findUser(ID id) {
        Optional<User> user = this.users.stream().filter(u -> u.getId().equals(id)).findFirst();
        return user.orElse(null);
    }
}
