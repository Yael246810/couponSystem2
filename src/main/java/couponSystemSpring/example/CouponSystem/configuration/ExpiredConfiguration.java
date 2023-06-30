package couponSystemSpring.example.CouponSystem.configuration;

import couponSystemSpring.example.CouponSystem.jobs.ClearExpiredTokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ExpiredConfiguration {
    @Autowired
    ClearExpiredTokens clearExpiredTokens;
    ExpiredConfiguration expiredConfiguration;
}
