package user.application.usecases.finduser;

import core.valueobjects.ID;

public class FindUserInput {
    private final ID id;

    private FindUserInput(ID id) {
        this.id = id;
    }

    FindUserInput with(ID id) {
        return new FindUserInput(id);
    }

    public ID getId() {
        return this.id;
    }
}
