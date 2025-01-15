package waterflow.infra.panache.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "water_sources")
public class PanacheWaterSourceEntity extends PanacheEntityBase {

  @Id
  private String id;

  private String type;

  @Column(name = "max_capacity")
  private Double maxCapacity;

  @Column(name = "safety_threshold")
  private Double safetyThreshold;

  @Column(name = "current_volume")
  private Double currentVolume;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public PanacheWaterSourceEntity() {}

  public PanacheWaterSourceEntity(
      String id,
      String type,
      Double maxCapacity,
      Double safetyThreshold,
      Double currentVolume,
      LocalDateTime createdAt,
      LocalDateTime updatedAt
      ) {
    this.id = id;
    this.type = type;
    this.maxCapacity = maxCapacity;
    this.safetyThreshold = safetyThreshold;
    this.currentVolume = currentVolume;
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

  public Double getMaxCapacity() {
    return maxCapacity;
  }

  public void setMaxCapacity(Double maxCapacity) {
    this.maxCapacity = maxCapacity;
  }

  public Double getSafetyThreshold() {
    return safetyThreshold;
  }

  public void setSafetyThreshold(Double safetyThreshold) {
    this.safetyThreshold = safetyThreshold;
  }

  public Double getCurrentVolume() {
    return currentVolume;
  }

  public void setCurrentVolume(Double currentVolume) {
    this.currentVolume = currentVolume;
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
