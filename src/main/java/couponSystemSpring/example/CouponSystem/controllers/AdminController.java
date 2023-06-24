package couponSystemSpring.example.CouponSystem.controllers;

import couponSystemSpring.example.CouponSystem.beans.Company;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.services.AdminService;
import couponSystemSpring.example.CouponSystem.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//TODO: to put verbs on each method, and to check if it needs other things
@RestController
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("login/admin")
    boolean login(){
        return ((ClientService) adminService).login("admin@admin.com","1234");
    }
    //TODO: do I really need getIdFromDB? for a customer?
    @GetMapping("DB/id")
    int getIdFromDB(@RequestParam String email){
        return adminService.getIdFromDB(email);
    }
    @GetMapping("add company/companies")
    void add(@RequestParam Company company) throws Exception {
        adminService.add(company);
    }
    @GetMapping("update company/companies")
    void updateCompany(@RequestParam Company company) throws CouponSystemException {
        adminService.updateCompany(company);
    }
    @GetMapping("delete company/companies")
    void deleteCompany(@RequestParam Company company) throws CouponSystemException {
        adminService.deleteCompany(company);
    }
    @GetMapping("/companies")
    List<Company> getAllCompanies(){
        return adminService.getAllCompanies();
    }
    @GetMapping("companies/company")
    Optional<Company> getSingleCompany(@PathVariable int companyId) throws CouponSystemException {
        return adminService.getSingleCompany(companyId);
    }
    @GetMapping("customers/customer")
    void addCustomer(@RequestParam Customer customer) throws CouponSystemException {
        adminService.addCustomer(customer);
    }
    @GetMapping("customers/update customer")
    void updateCustomer(@PathVariable int customerId,@RequestParam Customer customer) throws CouponSystemException {
        adminService.updateCustomer(customerId,customer);
    }
    @GetMapping("customers/delete customer")
    void deleteCustomer(@RequestParam Customer customer) throws CouponSystemException {
        adminService.deleteCustomer(customer);
    }
    @GetMapping("customers/")
    List<Customer> getAllCustomers(){
        return adminService.getAllCustomers();
    }
    @GetMapping("customers/customer")
    Optional<Customer> getSingleCustomer(@PathVariable int customerId) throws CouponSystemException {
        return adminService.getSingleCustomer(customerId);
    }
}
