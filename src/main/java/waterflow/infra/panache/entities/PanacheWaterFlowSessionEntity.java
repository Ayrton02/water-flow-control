package waterflow.infra.panache.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import user.infra.panache.entities.PanacheUserEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "water_flow_sessions")
public class PanacheWaterFlowSessionEntity extends PanacheEntityBase {

  @Id
  private String id;

  private String status;

  @Column(name = "started_at")
  private LocalDateTime startedAt;

  @Column(name = "finished_at")
  private LocalDateTime finishedAt;

  @OneToOne
  @JoinColumn(name = "user_id")
  private PanacheUserEntity user;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public PanacheWaterFlowSessionEntity() {}

  public PanacheWaterFlowSessionEntity(
      String id,
      LocalDateTime startedAt,
      LocalDateTime finishedAt,
      String status,
      PanacheUserEntity user,
      LocalDateTime createdAt,
      LocalDateTime updatedAt
      ) {
    this.id = id;
    this.startedAt = startedAt;
    this.finishedAt = finishedAt;
    this.status = status;
    this.user = user;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  public void setStartedAt(LocalDateTime startedAt) {
    this.startedAt = startedAt;
  }

  public LocalDateTime getFinishedAt() {
    return finishedAt;
  }

  public void setFinishedAt(LocalDateTime finishedAt) {
    this.finishedAt = finishedAt;
  }

  public PanacheUserEntity getUser() {
    return user;
  }

  public void setUser(PanacheUserEntity user) {
    this.user = user;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
