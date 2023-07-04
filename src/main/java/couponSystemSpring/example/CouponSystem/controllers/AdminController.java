package couponSystemSpring.example.CouponSystem.controllers;

import couponSystemSpring.example.CouponSystem.beans.ClientType;
import couponSystemSpring.example.CouponSystem.beans.Company;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.exceptions.ErrorMessage;
import couponSystemSpring.example.CouponSystem.security.TokenService;
import couponSystemSpring.example.CouponSystem.services.AdminService;
import couponSystemSpring.example.CouponSystem.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
//TODO: there is a problem with almost all get methods
@RestController
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private TokenService tokenService;
//    @PostMapping("login")
//    boolean login(@RequestHeader(value = "Authorization") UUID token,@RequestParam String email, @RequestParam String password) throws CouponSystemException {
//        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)){
//            throw new CouponSystemException(ErrorMessage.SECURITY_EXCEPTION_USER_NOT_ALLOWED);
//        }
//        return ((ClientService) adminService).login("admin@admin.com","1234");
//    }

    @PostMapping("company")
    @ResponseStatus(HttpStatus.CREATED)
    void addCompany(@RequestHeader("Authorization") String token, @RequestBody Company company) throws Exception {
        System.out.println("Adding the company: " + company + ", token: " +token);
        adminService.add(company);
    }

    @PutMapping("companies/company")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCompany(@RequestHeader("Authorization") String token,@RequestBody Company company) throws CouponSystemException {
        adminService.updateCompany(company);
    }
    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCompany(@RequestHeader("Authorization") String token,@PathVariable int companyId) throws CouponSystemException {
        adminService.deleteCompany(companyId);
    }
    @GetMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    List<Company> getAllCompanies(@RequestHeader(value = "Authorization") String token){
        return adminService.getAllCompanies();
    }
    //TODO: It doesn't work
    @GetMapping("companies/{companyId}")
    Optional<Company> getSingleCompany(@RequestHeader(value = "Authorization") String token, @PathVariable int companyId) throws CouponSystemException {
        Optional<Company> optionalCompany = adminService.getSingleCompany(companyId);
        return optionalCompany;
    }
    @PostMapping("customer")
    @ResponseStatus(HttpStatus.CREATED)
    void addCustomer(@RequestHeader(value = "Authorization") String token,@RequestBody Customer customer) throws CouponSystemException {
        adminService.addCustomer(customer);
    }
    @PutMapping("customer/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCustomer(@RequestHeader(value = "Authorization") String token,@PathVariable int customerId,@RequestBody Customer customer) throws CouponSystemException {
        adminService.updateCustomer(customerId,customer);
    }
    @DeleteMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@RequestHeader(value = "Authorization") String token,@PathVariable int customerId) throws CouponSystemException {
        adminService.deleteCustomer(customerId);
    }
    @GetMapping("customers")
    List<Customer> getAllCustomers(@RequestHeader(value = "Authorization") String token){
        return adminService.getAllCustomers();
    }
    @GetMapping("customers/{customerId}")
    Optional<Customer> getSingleCustomer(@RequestHeader(value = "Authorization") String token,@PathVariable int customerId) throws CouponSystemException {
        return adminService.getSingleCustomer(customerId).orElseThrow();
    }
}
