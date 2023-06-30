package couponSystemSpring.example.CouponSystem.jobs;

import couponSystemSpring.example.CouponSystem.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClearExpiredTokens {
    @Autowired
    private TokenService tokenService;
    @Scheduled(fixedRate = 1000*60)
    public void clearExpiredTokens(){
        System.out.println("thread of expired tokens is here");
        tokenService.clear();
        System.out.println("thread deleted expired tokens");
    }
}
