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
@CrossOrigin
public class AdminController extends BaseController {
    @Autowired
    private AdminService adminService;

    @PostMapping("company")
    @ResponseStatus(HttpStatus.CREATED)
    Company addCompany(@RequestHeader(value = "Authorization") String token,@RequestBody Company company) throws Exception {
        validateToken(token);
        return adminService.add(company);
    }

    @PutMapping("companies/company")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCompany(@RequestHeader(value = "Authorization") String token,@RequestBody Company company) throws CouponSystemException {
        validateToken(token);
        adminService.updateCompany(company);
    }
    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCompany(@RequestHeader(value = "Authorization") String token,@PathVariable int companyId) throws CouponSystemException {
        validateToken(token);
        adminService.deleteCompany(companyId);
    }
    @GetMapping("companies")
    @ResponseStatus(HttpStatus.OK)
    List<Company> getAllCompanies(@RequestHeader(value = "Authorization") String token) throws CouponSystemException {
        validateToken(token);
        return adminService.getAllCompanies();
    }
    @GetMapping("companies/{companyId}")
    Optional<Company> getSingleCompany(@RequestHeader(value = "Authorization") String token,@PathVariable int companyId) throws CouponSystemException {
        validateToken(token);
        Optional<Company> optionalCompany = adminService.getSingleCompany(companyId);
        return optionalCompany;
    }
    @PostMapping("customer")
    @ResponseStatus(HttpStatus.CREATED)
     Customer addCustomer(@RequestHeader(value = "Authorization") String token,@RequestBody Customer customer) throws CouponSystemException {
        validateToken(token);
        return adminService.addCustomer(customer);
    }
    @PutMapping("customer/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Customer updateCustomer(@RequestHeader(value = "Authorization") String token,@PathVariable int customerId,@RequestBody Customer customer) throws CouponSystemException {
        validateToken(token);
        return adminService.updateCustomer(customerId,customer);
    }
    @DeleteMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer (@RequestHeader(value = "Authorization") String token,@PathVariable int customerId) throws CouponSystemException {
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
