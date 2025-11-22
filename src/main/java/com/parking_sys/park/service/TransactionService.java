package com.parking_sys.park.service;

import com.parking_sys.park.model.*;
import com.parking_sys.park.repository.ParkingTransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    private final ParkingTransactionRepository txRepo;

    public TransactionService(ParkingTransactionRepository txRepo) { this.txRepo = txRepo; }

    @Transactional
    public ParkingTransaction createActive(Vehicle v, ParkingSpot s) {
        ParkingTransaction tx = new ParkingTransaction();
        tx.setVehicle(v);
        tx.setSpot(s);
        tx.setCheckinTime(LocalDateTime.now());
        tx.setStatus("ACTIVE");
        return txRepo.save(tx);
    }

    @Transactional
    public ParkingTransaction checkout(ParkingTransaction tx, BigDecimal fee) {
        tx.setCheckoutTime(LocalDateTime.now());
        tx.setFee(fee);
        tx.setStatus("COMPLETED");
        return txRepo.save(tx);
    }
}
