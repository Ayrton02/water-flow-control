package user.domain.entities;

import core.baseclasses.BaseEntity;
import core.valueobjects.ID;
import core.valueobjects.UUID;
import user.domain.valueobjects.DocumentNumber;

public class User extends BaseEntity {
    private final String name;
    private final DocumentNumber documentNumber;

    public User(String name, String documentNumber) {
        super(UUID.generate());
        this.name = name;
        this.documentNumber = new DocumentNumber(documentNumber);
    }

    public User(ID id, String name, String documentNumber) {
        super(id);
        this.name = name;
        this.documentNumber = new DocumentNumber(documentNumber);
    }

    public String getName() {
        return name;
    }

    public DocumentNumber getDocumentNumber() {
        return documentNumber;
    }

    @Override
    public String toString() {
        return String.format(
            "User[id=%s, name=%s, documentNumber=%s, createdAt=%s, updatedAt=%s]",
            this.getId().toString(),
            this.name,
            this.documentNumber.toString(),
            this.getCreatedAt().toString(),
            this.getUpdatedAt().toString()
        );
    }
}
