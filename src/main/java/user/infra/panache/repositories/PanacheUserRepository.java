package user.infra.panache.repositories;

import core.valueobjects.ID;
import core.valueobjects.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import user.application.usecases.createuser.CreateUserRepository;
import user.application.usecases.finduser.FindUserRepository;
import user.domain.entities.User;
import user.infra.panache.entities.PanacheUserEntity;

import java.util.Optional;

@ApplicationScoped
public class PanacheUserRepository implements FindUserRepository, CreateUserRepository {
    @Override
    @Transactional
    public void save(User user) {
        PanacheUserEntity.persist(this.toEntity(user));
    }

    @Override
    public User findUser(ID id) {
        Optional<PanacheUserEntity> entity = PanacheUserEntity.find("id", id.getValue()).firstResultOptional();
        return entity.map(this::toModel).orElse(null);
    }

    private PanacheUserEntity toEntity(User user) {
        return new PanacheUserEntity(
                user.getId().getValue(),
                user.getDocumentNumber().getValue(),
                user.getName(),
                user.getCreatedAt().toLocalDateTime(),
                user.getUpdatedAt().toLocalDateTime()
        );
    }

    private User toModel(PanacheUserEntity entity) {
        return new User(
                UUID.from(entity.getId()),
                entity.getName(),
                entity.getDocumentNumber()
        );
    }
}
