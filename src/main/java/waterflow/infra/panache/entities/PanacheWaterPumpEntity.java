package waterflow.infra.panache.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "water_pumps")
public class PanacheWaterPumpEntity extends PanacheEntityBase {

  @Id
  private String id;

  private String type;

  @Column(name = "is_active")
  private Boolean isActive;

  private Double volume;

  @Column(name = "time_unit")
  private String timeUnit;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public PanacheWaterPumpEntity() {}

  public PanacheWaterPumpEntity(
      String id,
      String type,
      Boolean isActive,
      Double volume,
      String timeUnit,
      LocalDateTime createdAt,
      LocalDateTime updatedAt
      ) {
    this.id = id;
    this.type = type;
    this.isActive = isActive;
    this.volume = volume;
    this.timeUnit = timeUnit;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public Double getVolume() {
    return volume;
  }

  public void setVolume(Double volume) {
    this.volume = volume;
  }

  public String getTimeUnit() {
    return timeUnit;
  }

  public void setTimeUnit(String timeUnit) {
    this.timeUnit = timeUnit;
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

  @PreUpdate
  public void preUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
