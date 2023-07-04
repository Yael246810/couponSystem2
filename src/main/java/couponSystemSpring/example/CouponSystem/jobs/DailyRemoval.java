package couponSystemSpring.example.CouponSystem.jobs;

import couponSystemSpring.example.CouponSystem.repositories.CouponRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DailyRemoval {
    @Autowired
    private CouponRepository couponRepository;
    @Transactional
    @Scheduled(fixedRate = 1000*60)
    public void dailyExpiredCoupons() {
        //System.out.println("thread of expired coupons start working");
        couponRepository.deleteByEndDateBefore(LocalDate.now());
        //System.out.println("thread deleted expired coupon");
    }
}
