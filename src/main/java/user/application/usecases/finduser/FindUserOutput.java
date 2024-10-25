package user.application.usecases.finduser;

import user.domain.entities.User;

public class FindUserOutput {
    private final String id;
    private final String createdAt;
    private final String updatedAt;
    private final String name;
    private final String documentNumber;

    private FindUserOutput(String id, String createdAt, String updatedAt, String name, String documentNumber) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.documentNumber = documentNumber;
    }

    public static FindUserOutput from(User user) {
        return new FindUserOutput(
                user.getId().getValue(),
                user.getCreatedAt().toString(),
                user.getUpdatedAt().toString(),
                user.getName(),
                user.getDocumentNumber().toString()
        );
    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getName() {
        return name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }
}
