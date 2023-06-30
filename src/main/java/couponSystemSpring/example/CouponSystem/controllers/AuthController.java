package couponSystemSpring.example.CouponSystem.controllers;

import couponSystemSpring.example.CouponSystem.beans.User;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(User user) throws CouponSystemException {
        authService.register(user);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID login(User user) throws CouponSystemException {
        return authService.login(user);
    }
}
