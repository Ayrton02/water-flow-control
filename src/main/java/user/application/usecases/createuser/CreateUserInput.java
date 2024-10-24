package user.application.usecases.createuser;

public class CreateUserInput {
    private final String name;
    private final String documentNumber;

    CreateUserInput(String name, String documentNumber) {
        this.name = name;
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }
}
