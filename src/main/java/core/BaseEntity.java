package core;

import domain.valueobjects.ID;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    private final ID id;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected BaseEntity(ID id) {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
