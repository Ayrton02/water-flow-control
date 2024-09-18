package core;

import domain.valueobjects.ID;

import java.util.Date;

public abstract class BaseEntity {
    private final ID id;
    private final Date createdAt;
    private Date updatedAt;

    protected BaseEntity(ID id) {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
