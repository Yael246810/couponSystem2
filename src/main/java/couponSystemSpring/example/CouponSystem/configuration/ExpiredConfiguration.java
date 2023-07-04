package couponSystemSpring.example.CouponSystem.configuration;

import couponSystemSpring.example.CouponSystem.jobs.ClearExpiredTokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
//TODO: why do I need this class?
@Configuration
@EnableScheduling
public class ExpiredConfiguration {
    @Autowired
    ClearExpiredTokens clearExpiredTokens;
    ExpiredConfiguration expiredConfiguration;
}
