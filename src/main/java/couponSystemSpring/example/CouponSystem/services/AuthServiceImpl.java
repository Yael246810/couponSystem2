package couponSystemSpring.example.CouponSystem.services;

import couponSystemSpring.example.CouponSystem.beans.ClientType;
import couponSystemSpring.example.CouponSystem.beans.User;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.exceptions.ErrorMessage;
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
//    @Override
//    public void register(User user) throws CouponSystemException {
//        if (user.getType().equals(ClientType.ADMIN)){
//            throw new CouponSystemException(ErrorMessage.SECURITY_EXCEPTION_CANNOT_CREATE_ADMIN_USER);
//        }
//        if (userRepository.existsByEmail(user.getEmail())){
//            throw new CouponSystemException(ErrorMessage.SECURITY_EXCEPTION_USER_EMAIL);
//        }
//        userRepository.save(user);
//    }

    @Override
    public UUID login(User user) throws CouponSystemException {
        if (!userRepository.existsByEmailAndPassword(user.getEmail(), user.getPassword())){
            throw new CouponSystemException(ErrorMessage.SECURITY_EXCEPTION_USER_DOES_NOT_EXIST);
        }
        return tokenService.addToTokenList(user);
    }
}
