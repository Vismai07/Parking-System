package com.parking_sys.park.service;

import com.parking_sys.park.model.FeeRule;
import com.parking_sys.park.repository.FeeRuleRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class FeeService {
    private final FeeRuleRepository feeRepo;

    public FeeService(FeeRuleRepository feeRepo) { this.feeRepo = feeRepo; }

    public BigDecimal calculate(String vehicleType, LocalDateTime in, LocalDateTime out) {
        FeeRule rule = feeRepo.findByVehicleType(vehicleType)
                .orElseThrow(() -> new RuntimeException("No fee rule for " + vehicleType));
        long minutes = Math.max(1, Duration.between(in, out).toMinutes());
        long hours = (long) Math.ceil(minutes / 60.0);
        BigDecimal base = rule.getRatePerHour().multiply(BigDecimal.valueOf(hours));
        return base.max(rule.getMinFee());
    }
}
