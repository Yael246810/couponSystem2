package couponSystemSpring.example.CouponSystem.security;

import couponSystemSpring.example.CouponSystem.beans.ClientType;
import couponSystemSpring.example.CouponSystem.beans.User;

import java.util.UUID;

public interface TokenService {
    UUID addToTokenList(User user);
    boolean isUserAllowed(UUID token, ClientType clientType);
    void clear();
}
