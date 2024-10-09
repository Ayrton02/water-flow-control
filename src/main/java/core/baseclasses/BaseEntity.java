package core.baseclasses;

import core.valueobjects.DateTime;
import core.valueobjects.ID;

public abstract class BaseEntity {
    private final ID id;
    private final DateTime createdAt;
    private DateTime updatedAt;

    protected BaseEntity(ID id) {
        this.createdAt = DateTime.now();
        this.updatedAt = DateTime.now();
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
