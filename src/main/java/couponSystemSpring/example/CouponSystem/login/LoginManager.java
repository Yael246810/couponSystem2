package couponSystemSpring.example.CouponSystem.login;

import couponSystemSpring.example.CouponSystem.beans.ClientType;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.exceptions.ErrorMessage;
import couponSystemSpring.example.CouponSystem.repositories.CompanyRepository;
import couponSystemSpring.example.CouponSystem.services.AdminService;
import couponSystemSpring.example.CouponSystem.services.ClientService;
import couponSystemSpring.example.CouponSystem.services.CompanyService;
import couponSystemSpring.example.CouponSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Component
@Lazy
public class LoginManager {
//TODO: I don't understand if I need these lines with the compilation error
    @Autowired
    private AdminService adminService;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CompanyService companyService;

    public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
        switch (clientType){
            case ADMIN:
                if (((ClientService)adminService).login(email,password)){
                    return (ClientService) adminService;
                }
                break;
            case COMPANY:
                if (((ClientService)companyService).login(email,password)){
                    int id = companyService.getIdFromDB(email);
                    //((CompanyService) companyService).setCompanyId(id);
                    return (ClientService) companyService;
                }
                break;
            case CUSTOMER:
                if (((ClientService)customerService).login(email,password)){
                    int id = ((CustomerService) customerService).getIdFromDB(email);
                   // ((CustomerService) customerService).setCustomerId(id);
                    return (ClientService) customerService;
                }
                break;
        }
        throw new CouponSystemException(ErrorMessage.PROBLEM_WITH_THE_LOGIN);
    }
}
