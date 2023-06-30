//package couponSystemSpring.example.CouponSystem.controllers;
//
//import couponSystemSpring.example.CouponSystem.beans.ClientType;
//import couponSystemSpring.example.CouponSystem.beans.Company;
//import couponSystemSpring.example.CouponSystem.beans.Customer;
//import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
//import couponSystemSpring.example.CouponSystem.exceptions.ErrorMessage;
//import couponSystemSpring.example.CouponSystem.security.TokenService;
//import couponSystemSpring.example.CouponSystem.services.AdminService;
//import couponSystemSpring.example.CouponSystem.services.ClientService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
////TODO: to put verbs on each method, and to check if it needs other things. and to change the @GetMapping annotation if I delete then @DeleteMapping and so on...
//@RestController
//@RequestMapping("api/admin")
//public class AdminController {
//    @Autowired
//    private AdminService adminService;
//    @Autowired
//    private TokenService tokenService;
//    @GetMapping("login/admin")
//    boolean login(@RequestHeader(value = "Authorization") UUID token) throws CouponSystemException {
//        if (!tokenService.isUserAllowed(token, ClientType.ADMIN)){
//            throw new CouponSystemException(ErrorMessage.SECURITY_EXCEPTION_USER_NOT_ALLOWED);
//        }
//        return ((ClientService) adminService).login("admin@admin.com","1234");
//    }
//    //TODO: do I really need getIdFromDB? for a customer?
//    @GetMapping("DB/id")
//    int getIdFromDB(@RequestParam String email){
//        return adminService.getIdFromDB(email);
//    }
//    @GetMapping("company/add")
//    void add(@RequestParam Company company) throws Exception {
//        adminService.add(company);
//    }
//    @GetMapping("company/update")
//    void updateCompany(@RequestParam Company company) throws CouponSystemException {
//        adminService.updateCompany(company);
//    }
//    @GetMapping("companies/delete")
//    void deleteCompany(@RequestParam Company company) throws CouponSystemException {
//        adminService.deleteCompany(company);
//    }
//    @GetMapping("companies/get")
//    List<Company> getAllCompanies(){
//        return adminService.getAllCompanies();
//    }
//    @GetMapping("companies/company")
//    Optional<Company> getSingleCompany(@PathVariable int companyId) throws CouponSystemException {
//        return adminService.getSingleCompany(companyId);
//    }
//    @GetMapping("customer/add")
//    void addCustomer(@RequestParam Customer customer) throws CouponSystemException {
//        adminService.addCustomer(customer);
//    }
//    @GetMapping("customer/update")
//    void updateCustomer(@PathVariable int customerId,@RequestParam Customer customer) throws CouponSystemException {
//        adminService.updateCustomer(customerId,customer);
//    }
//    @GetMapping("customer/delete")
//    void deleteCustomer(@RequestParam Customer customer) throws CouponSystemException {
//        adminService.deleteCustomer(customer);
//    }
//    @GetMapping("customers/get")
//    List<Customer> getAllCustomers(){
//        return adminService.getAllCustomers();
//    }
//    @GetMapping("customers/customer")
//    Optional<Customer> getSingleCustomer(@PathVariable int customerId) throws CouponSystemException {
//        return adminService.getSingleCustomer(customerId).orElseThrow();
//    }
//}
