package user.application.usecases.createuser;

import user.domain.entities.User;

public record CreateUserOutput (
        String id,
        String name,
        String documentNumber,
        String createdAt,
        String updatedAt
) {
    static CreateUserOutput from(User user) {
        return new CreateUserOutput(
                user.getId().getValue(),
                user.getName(),
                user.getDocumentNumber().getValue(),
                user.getCreatedAt().toString(),
                user.getUpdatedAt().toString()
        );
    }
}
