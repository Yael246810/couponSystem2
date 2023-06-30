package couponSystemSpring.example.CouponSystem.jobs;

import couponSystemSpring.example.CouponSystem.repositories.CouponRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// TODO: to understand how to activate this - it's already activated with @Schedualing.
//@Component
public class DailyRemoval {
    @Autowired
    private CouponRepository couponRepository;
    @Transactional
    @Scheduled(fixedRate = 1000)
    public void dailyExpiredCoupons() {
        couponRepository.deleteByEndDateBefore(LocalDate.now());
    }
}
