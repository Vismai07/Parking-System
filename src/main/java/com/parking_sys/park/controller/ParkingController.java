package com.parking_sys.park.controller;

import com.parking_sys.park.controller.dto.CheckinRequest;
import com.parking_sys.park.controller.dto.CheckinResponse;
import com.parking_sys.park.controller.dto.CheckoutRequest;
import com.parking_sys.park.controller.dto.CheckoutResponse;
import com.parking_sys.park.model.ParkingSpot;
import com.parking_sys.park.model.ParkingTransaction;
import com.parking_sys.park.model.Vehicle;
import com.parking_sys.park.repository.ParkingTransactionRepository;
import com.parking_sys.park.service.AllocationService;
import com.parking_sys.park.service.FeeService;
import com.parking_sys.park.service.TransactionService;
import com.parking_sys.park.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private final VehicleService vehicleService;
    private final AllocationService allocationService;
    private final FeeService feeService;
    private final TransactionService txService;
    private final ParkingTransactionRepository txRepo;

    public ParkingController(
            VehicleService vehicleService,
            AllocationService allocationService,
            FeeService feeService,
            TransactionService txService,
            ParkingTransactionRepository txRepo
    ) {
        this.vehicleService = vehicleService;
        this.allocationService = allocationService;
        this.feeService = feeService;
        this.txService = txService;
        this.txRepo = txRepo;
    }

    // --------------------------------------------------------------------
    // CHECK-IN
    // --------------------------------------------------------------------
    @PostMapping("/checkin")
    @Transactional
    public ResponseEntity<CheckinResponse> checkin(@RequestBody CheckinRequest req) {

        // 1. Find or create vehicle
        Vehicle v = vehicleService.findOrCreate(req.licensePlate, req.vehicleType);

        // 2. Allocate a spot
        ParkingSpot spot = allocationService.allocate(req.lotId, req.vehicleType);

        // 3. Mark occupied
        spot.setIsOccupied(true);

        // 4. Create ACTIVE transaction
        ParkingTransaction tx = txService.createActive(v, spot);

        // 5. Build response
        CheckinResponse res = new CheckinResponse();
        res.transactionId = tx.getId();
        res.spotId = spot.getId();
        res.spotLabel = spot.getSpotLabel();
        res.floor = spot.getFloor();

        return ResponseEntity.ok(res);
    }

    // --------------------------------------------------------------------
    // CHECK-OUT
    // --------------------------------------------------------------------
    @PostMapping("/checkout")
    @Transactional
    public ResponseEntity<CheckoutResponse> checkout(@RequestBody CheckoutRequest req) {

        // 1. Fetch ACTIVE transaction
        ParkingTransaction tx = txRepo.findById(req.transactionId)
                .orElseThrow(() -> new RuntimeException("Invalid transaction"));

        if (!"ACTIVE".equals(tx.getStatus())) {
            throw new RuntimeException("Transaction already completed");
        }

        // 2. Calculate fee
        LocalDateTime now = LocalDateTime.now();
        BigDecimal fee = feeService.calculate(
                tx.getVehicle().getVehicleType(),
                tx.getCheckinTime(),
                now
        );

        // 3. Close transaction
        tx = txService.checkout(tx, fee);

        // 4. Free the spot
        ParkingSpot spot = tx.getSpot();
        spot.setIsOccupied(false);

        // 5. Build response using all-args constructor
        CheckoutResponse res = new CheckoutResponse(
                tx.getId(),
                tx.getCheckinTime(),
                now,
                fee.doubleValue(),
                spot.getSpotLabel()
        );

        // 6. Return response
        return ResponseEntity.ok(res);
    }
}

