package com.parking_sys.park.repository;

import com.parking_sys.park.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> { }
