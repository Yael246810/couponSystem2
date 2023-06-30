package couponSystemSpring.example.CouponSystem.controllers;

import couponSystemSpring.example.CouponSystem.beans.Company;
import couponSystemSpring.example.CouponSystem.beans.User;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.login.LoginManager;
import couponSystemSpring.example.CouponSystem.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID login(@RequestBody User user) throws CouponSystemException {
        System.out.println("Login process started the user: " + user);
        return authService.login(user);
    }
}
