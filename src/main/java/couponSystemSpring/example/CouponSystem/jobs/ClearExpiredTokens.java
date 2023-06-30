package couponSystemSpring.example.CouponSystem.jobs;

import couponSystemSpring.example.CouponSystem.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class ClearExpiredTokens {
    @Autowired
    private TokenService tokenService;
    @Scheduled(fixedRate = 1000*60)
    public void clearExpiredTokens(){
        tokenService.clear();
    }
}
