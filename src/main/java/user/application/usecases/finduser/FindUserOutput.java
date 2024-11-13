package user.application.usecases.finduser;

import user.domain.entities.User;

public record FindUserOutput (
        String id,
        String name,
        String documentNumber,
        String createdAt,
        String updatedAt
) {
    public static FindUserOutput from(User user) {
        return new FindUserOutput(
                user.getId().getValue(),
                user.getName(),
                user.getDocumentNumber().getValue(),
                user.getCreatedAt().toString(),
                user.getUpdatedAt().toString()
        );
    }
}
