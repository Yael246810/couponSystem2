package couponSystemSpring.example.CouponSystem.controllers;

import couponSystemSpring.example.CouponSystem.beans.ClientType;
import couponSystemSpring.example.CouponSystem.beans.Company;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("api/admin")
public class AdminController extends BaseController {
    @Autowired
    private AdminService adminService;

    @PostMapping("company")
    @ResponseStatus(HttpStatus.CREATED)
    void addCompany(@RequestHeader("Authorization") String token, @RequestBody Company company) throws Exception {
        validateToken(token);
        adminService.add(company);
    }

    @PutMapping("companies/company")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCompany(@RequestHeader("Authorization") String token,@RequestBody Company company) throws CouponSystemException {
        validateToken(token);
        adminService.updateCompany(company);
    }
    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCompany(@RequestHeader("Authorization") String token,@PathVariable int companyId) throws CouponSystemException {
        validateToken(token);
        adminService.deleteCompany(companyId);
    }
    @GetMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    List<Company> getAllCompanies(@RequestHeader(value = "Authorization") String token) throws CouponSystemException {
        validateToken(token);
        return adminService.getAllCompanies();
    }
    //TODO: It doesn't work
    @GetMapping("companies/{companyId}")
    Optional<Company> getSingleCompany(@RequestHeader(value = "Authorization") String token, @PathVariable int companyId) throws CouponSystemException {
        validateToken(token);
        Optional<Company> optionalCompany = adminService.getSingleCompany(companyId);
        return optionalCompany;
    }
    @PostMapping("customer")
    @ResponseStatus(HttpStatus.CREATED)
    void addCustomer(@RequestHeader(value = "Authorization") String token,@RequestBody Customer customer) throws CouponSystemException {
        validateToken(token);
        adminService.addCustomer(customer);
    }
    @PutMapping("customer/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCustomer(@RequestHeader(value = "Authorization") String token,@PathVariable int customerId,@RequestBody Customer customer) throws CouponSystemException {
        validateToken(token);
        adminService.updateCustomer(customerId,customer);
    }
    @DeleteMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@RequestHeader(value = "Authorization") String token,@PathVariable int customerId) throws CouponSystemException {
        validateToken(token);
        adminService.deleteCustomer(customerId);
    }
    @GetMapping("customers")
    List<Customer> getAllCustomers(@RequestHeader(value = "Authorization") String token) throws CouponSystemException {
        validateToken(token);
        return adminService.getAllCustomers();
    }
    @GetMapping("customers/{customerId}")
    Optional<Customer> getSingleCustomer(@RequestHeader(value = "Authorization") String token,@PathVariable int customerId) throws CouponSystemException {
        validateToken(token);
        return adminService.getSingleCustomer(customerId).orElseThrow();
    }

    @Override
    protected ClientType getClientType() {
        return ClientType.ADMIN;
    }
}
