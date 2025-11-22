package com.parking_sys.park.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_spot")
public class ParkingSpot {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "lot_id")
    private ParkingLot lot;

    @Column(name = "floor") private Integer floor;
    @Column(name = "spot_label") private String spotLabel;
    @Column(name = "spot_size") private String spotSize; // MOTORCYCLE, CAR, BUS
    @Column(name = "is_occupied") private Boolean isOccupied = false;
    @Column(name = "distance_score") private Integer distanceScore = 0;
    @Column(name = "created_at") private LocalDateTime createdAt;
    @Column(name = "updated_at") private LocalDateTime updatedAt;

    public ParkingSpot() {}

    public Long getId() { return id; }
    public ParkingLot getLot() { return lot; }
    public void setLot(ParkingLot lot) { this.lot = lot; }
    public Integer getFloor() { return floor; }
    public void setFloor(Integer floor) { this.floor = floor; }
    public String getSpotLabel() { return spotLabel; }
    public void setSpotLabel(String spotLabel) { this.spotLabel = spotLabel; }
    public String getSpotSize() { return spotSize; }
    public void setSpotSize(String spotSize) { this.spotSize = spotSize; }
    public Boolean getIsOccupied() { return isOccupied; }
    public void setIsOccupied(Boolean isOccupied) { this.isOccupied = isOccupied; }
    public Integer getDistanceScore() { return distanceScore; }
    public void setDistanceScore(Integer distanceScore) { this.distanceScore = distanceScore; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
