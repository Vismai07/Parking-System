package com.parking_sys.park.service;

import com.parking_sys.park.model.ParkingSpot;
import com.parking_sys.park.repository.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationService {
    private final ParkingSpotRepository spotRepo;

    public AllocationService(ParkingSpotRepository spotRepo) { this.spotRepo = spotRepo; }

    private List<String> compatibleSizes(String vehicleType) {
        return switch (vehicleType.toUpperCase()) {
            case "MOTORCYCLE" -> List.of("MOTORCYCLE", "CAR");
            case "CAR" -> List.of("CAR");
            case "BUS" -> List.of("BUS");
            default -> List.of("CAR"); // safe default
        };
    }

    @Transactional
    public ParkingSpot allocate(Long lotId, String vehicleType) {
        List<String> sizes = compatibleSizes(vehicleType);
        var candidates = spotRepo.findCandidates(lotId, sizes);
        if (candidates.isEmpty()) throw new RuntimeException("No spot available");

        ParkingSpot chosen = candidates.get(0);
        int updated = spotRepo.markOccupiedIfFree(chosen.getId());
        if (updated == 0) throw new RuntimeException("Spot taken concurrently, retry");
        return chosen;
    }
}
