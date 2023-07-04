package couponSystemSpring.example.CouponSystem.controllers;

import couponSystemSpring.example.CouponSystem.beans.ClientType;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.exceptions.ErrorMessage;
import couponSystemSpring.example.CouponSystem.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public abstract class BaseController {
    @Autowired
    private TokenService tokenService;

    protected abstract ClientType getClientType ();

    protected void validateToken(String token) throws CouponSystemException {
        UUID tokenUUID = UUID.fromString(token);

        if (!tokenService.isUserAllowed(tokenUUID, getClientType())) {
            throw new CouponSystemException(ErrorMessage.SECURITY_EXCEPTION_USER_NOT_ALLOWED);
        }
    }
}
