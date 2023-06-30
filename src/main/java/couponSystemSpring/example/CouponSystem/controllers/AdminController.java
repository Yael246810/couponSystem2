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

@RestController
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private TokenService tokenService;
    @PostMapping("login/admin")
    boolean login(@RequestHeader(value = "Authorization") UUID token,@RequestParam String email, @RequestParam String password) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)){
            throw new CouponSystemException(ErrorMessage.SECURITY_EXCEPTION_USER_NOT_ALLOWED);
        }
        return ((ClientService) adminService).login("admin@admin.com","1234");
    }

    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    void addCompany(@RequestHeader("Authorization") String token, @RequestBody Company company) throws Exception {
        System.out.println("Adding the company: " + company + ", token: " +token);
        adminService.add(company);
    }

    @PutMapping("company/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCompany(@RequestHeader(value = "Authorization") UUID token,@RequestBody Company company) throws CouponSystemException {
        adminService.updateCompany(company);
    }
    @DeleteMapping("/company/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCompany(@RequestHeader(value = "Authorization") UUID token,@RequestBody Company company) throws CouponSystemException {
        adminService.deleteCompany(company.getId(), company);
    }
    @GetMapping("/api/admin/companies")
    List<Company> getAllCompanies(@RequestHeader(value = "Authorization") UUID token){
        return adminService.getAllCompanies();
    }
    @GetMapping("/{companyId}")
    Optional<Company> getSingleCompany(@RequestHeader(value = "Authorization") UUID token,@PathVariable int companyId) throws CouponSystemException {
        return adminService.getSingleCompany(companyId);
    }
    @PostMapping("/api/admin/customers")
    @ResponseStatus(HttpStatus.CREATED)
    void addCustomer(@RequestHeader(value = "Authorization") UUID token,@RequestBody Customer customer) throws CouponSystemException {
        adminService.addCustomer(customer);
    }
    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCustomer(@RequestHeader(value = "Authorization") UUID token,@PathVariable int customerId,@RequestBody Customer customer) throws CouponSystemException {
        adminService.updateCustomer(customerId,customer);
    }
    @DeleteMapping("{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@RequestHeader(value = "Authorization") UUID token,@PathVariable int customerId,@RequestBody Customer customer) throws CouponSystemException {
        adminService.deleteCustomer(customer);
    }
    @GetMapping("/api/admin/customers")
    List<Customer> getAllCustomers(@RequestHeader(value = "Authorization") UUID token){
        return adminService.getAllCustomers();
    }
    @GetMapping("/{customerId}")
    Optional<Customer> getSingleCustomer(@RequestHeader(value = "Authorization") UUID token,@PathVariable int customerId) throws CouponSystemException {
        return adminService.getSingleCustomer(customerId).orElseThrow();
    }
}
