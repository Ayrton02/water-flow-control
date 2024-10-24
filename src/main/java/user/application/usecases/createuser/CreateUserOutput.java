package user.application.usecases.createuser;

import core.valueobjects.DateTime;
import user.domain.entities.User;

public class CreateUserOutput {
    private final String name;
    private final String documentNumber;
    private final DateTime createdAt;

    private CreateUserOutput(String name, String documentNumber, DateTime createdAt) {
        this.name = name;
        this.documentNumber = documentNumber;
        this.createdAt = createdAt;
    }

    static CreateUserOutput from(User user) {
        return new CreateUserOutput(
                user.getName(),
                user.getDocumentNumber().toString(),
                user.getCreatedAt()
        );
    }

    public String getName() {
        return name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }
}
