package couponSystemSpring.example.CouponSystem.services;

import couponSystemSpring.example.CouponSystem.beans.User;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public interface AuthService {

    void register(@RequestBody User user) throws CouponSystemException;
    UUID login(@RequestBody User user) throws CouponSystemException;
}
