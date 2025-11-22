package com.parking_sys.park.service;

import com.parking_sys.park.model.Vehicle;
import com.parking_sys.park.repository.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private final VehicleRepository repo;

    public VehicleService(VehicleRepository repo) { this.repo = repo; }

    public Vehicle findOrCreate(String plate, String type) {
        return repo.findByLicensePlate(plate)
                .orElseGet(() -> repo.save(new Vehicle(plate, type)));
    }
}
