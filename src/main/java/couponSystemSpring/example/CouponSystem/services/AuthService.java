package couponSystemSpring.example.CouponSystem.services;

import couponSystemSpring.example.CouponSystem.beans.ClientType;
import couponSystemSpring.example.CouponSystem.beans.User;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public interface AuthService {
    AuthServiceImpl.LoginResponseData login(@RequestBody User user) throws CouponSystemException;
    User CreateUser(String email, String password, ClientType clientType);
    User UpdateUser(int id,String email, String password);
    void deleteUser(int id);
}
