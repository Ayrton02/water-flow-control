package user.application.service;

import core.valueobjects.UUID;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import user.application.usecases.createuser.CreateUserInput;
import user.application.usecases.createuser.ICreateUserUseCase;
import user.application.usecases.finduser.FindUserInput;
import user.application.usecases.finduser.IFindUserUseCase;
import user.presenters.dto.UserRequestDTO;
import user.presenters.dto.UserResponseDTO;
import user.presenters.mappers.UserMapper;

@ApplicationScoped
public class UserService {
    private final ICreateUserUseCase createUserUseCase;
    private final IFindUserUseCase findUserUseCase;

    @Inject
    public UserService(ICreateUserUseCase createUserUseCase, IFindUserUseCase findUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.findUserUseCase = findUserUseCase;
    }

    public UserResponseDTO createUser(UserRequestDTO request) {
        return UserMapper.toResponseDTO(
                this.createUserUseCase.execute(
                        new CreateUserInput(
                                request.name(),
                                request.documentNumber()
                        )
                )
        );
    }

    public UserResponseDTO findUser(String id) {
        return UserMapper.toResponseDTO(
                this.findUserUseCase.execute(
                        new FindUserInput(UUID.from(id))
                )
        );
    }
}
