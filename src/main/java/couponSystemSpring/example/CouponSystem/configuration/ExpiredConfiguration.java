package couponSystemSpring.example.CouponSystem.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
//TODO: if I have @EnabledScheduling here, I can delete the same annotation in the CouponSystemApplication in the main source.
public class ExpiredConfiguration {
}
