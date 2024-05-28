package core;

import domain.valueobjects.ID;

import java.util.Date;

public abstract class BaseEntity {
    protected ID id;
    protected Date createdAt;
    protected Date updatedAt;

    public BaseEntity(ID id) {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.id = id;
    }
}
