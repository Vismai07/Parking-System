package com.parking_sys.park.repository;

import com.parking_sys.park.model.ParkingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingTransactionRepository extends JpaRepository<ParkingTransaction, Long> {
    Optional<ParkingTransaction> findByVehicleIdAndStatus(Long vehicleId, String status);
}
