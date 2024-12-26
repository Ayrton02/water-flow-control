package waterflow.infra.panache.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "water_containers")
public class PanacheWaterContainerEntity extends PanacheEntityBase {

  @Id
  private String id;

  private String type;

  @Column(name = "max_capacity")
  private Double maxCapacity;

  @Column(name = "current_volume")
  private Double currentVolume;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public PanacheWaterContainerEntity() {}

  public PanacheWaterContainerEntity(
      String id,
      String type,
      Double maxCapacity,
      Double currentVolume,
      LocalDateTime createdAt,
      LocalDateTime updatedAt
      ) {
    this.id = id;
    this.type = type;
    this.maxCapacity = maxCapacity;
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
}
