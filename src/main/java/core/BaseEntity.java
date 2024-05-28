package core;

import domain.valueobjects.ID;

import java.util.Date;

public abstract class Entity<T> {
    protected ID id;
    protected Date createdAt;
    protected Date updatedAt;
    protected T data;

    public Entity(ID id, T data) {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.data = data;
        this.id = id;
    }
}
