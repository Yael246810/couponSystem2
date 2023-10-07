package couponSystemSpring.example.CouponSystem.services;

import couponSystemSpring.example.CouponSystem.beans.ClientType;
import couponSystemSpring.example.CouponSystem.beans.User;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.exceptions.ErrorMessage;
import couponSystemSpring.example.CouponSystem.login.LoginManager;
import couponSystemSpring.example.CouponSystem.repositories.UserRepository;
import couponSystemSpring.example.CouponSystem.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private LoginManager loginManager;

    @Override
    public LoginResponseData login(User user) throws CouponSystemException {
        if (!userRepository.existsByEmailAndPassword(user.getEmail(), user.getPassword())){
            throw new CouponSystemException(ErrorMessage.SECURITY_EXCEPTION_USER_DOES_NOT_EXIST);
        }

        loginManager.login(user.getEmail(), user.getPassword(), user.getType());

        String token = tokenService.addToTokenList(user).toString();
        long id = userRepository.getIdByEmail(user.getEmail());
        LoginResponseData responseData = new LoginResponseData(token, id);
        return responseData;
    }

    @Override
    public User CreateUser(int id,String email, String password, ClientType clientType) {
        User user = User.builder()
                .userId(id)
                .email(email).type(clientType).password(password).build();
        return userRepository.save(user);
    }

    public class LoginResponseData {
        public LoginResponseData(String token, long id) {
            this.token = token;
            this.id = id;
        }

        public String getToken() {
            return token;
        }

        private String token;

        public long getId() {
            return id;
        }

        private long id;
    }
}
