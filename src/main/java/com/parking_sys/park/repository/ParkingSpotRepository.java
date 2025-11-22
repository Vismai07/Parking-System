package com.parking_sys.park.repository;

import com.parking_sys.park.model.ParkingSpot;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    @Query("SELECT s FROM ParkingSpot s WHERE s.lot.id = :lotId AND s.spotSize IN :sizes AND s.isOccupied = FALSE " +
        "ORDER BY s.distanceScore ASC, s.floor ASC, s.spotLabel ASC")
    List<ParkingSpot> findCandidates(@Param("lotId") Long lotId, @Param("sizes") List<String> sizes);

    @Modifying
    @Query("UPDATE ParkingSpot s SET s.isOccupied = TRUE WHERE s.id = :id AND s.isOccupied = FALSE")
    int markOccupiedIfFree(@Param("id") Long id);
}
