package couponSystemSpring.example.CouponSystem.services;

import couponSystemSpring.example.CouponSystem.beans.User;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.exceptions.ErrorMessage;
import couponSystemSpring.example.CouponSystem.login.LoginManager;
import couponSystemSpring.example.CouponSystem.repositories.UserRepository;
import couponSystemSpring.example.CouponSystem.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private LoginManager loginManager;

    @Override
    public UUID login(User user) throws CouponSystemException {
        if (!userRepository.existsByEmailAndPassword(user.getEmail(), user.getPassword())){
            throw new CouponSystemException(ErrorMessage.SECURITY_EXCEPTION_USER_DOES_NOT_EXIST);
        }

        loginManager.login(user.getEmail(), user.getPassword(), user.getType());

        return tokenService.addToTokenList(user);
    }
}
