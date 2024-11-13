package user.presenters.mappers;

import user.application.usecases.createuser.CreateUserOutput;
import user.application.usecases.finduser.FindUserOutput;
import user.presenters.dto.UserResponseDTO;

public class UserMapper {
    public static UserResponseDTO toResponseDTO(CreateUserOutput output) {
        return new UserResponseDTO(
                output.id(),
                output.name(),
                output.documentNumber(),
                output.createdAt(),
                output.updatedAt()
        );
    }

    public static UserResponseDTO toResponseDTO(FindUserOutput output) {
        return new UserResponseDTO(
                output.id(),
                output.name(),
                output.documentNumber(),
                output.createdAt(),
                output.updatedAt()
        );
    }
}
