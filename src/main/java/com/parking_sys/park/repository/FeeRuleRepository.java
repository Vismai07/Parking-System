package com.parking_sys.park.repository;

import com.parking_sys.park.model.FeeRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeeRuleRepository extends JpaRepository<FeeRule, Long> {
    Optional<FeeRule> findByVehicleType(String vehicleType);
}
