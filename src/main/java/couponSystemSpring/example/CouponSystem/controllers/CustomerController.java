//package couponSystemSpring.example.CouponSystem.controllers;
//
//import couponSystemSpring.example.CouponSystem.beans.Category;
//import couponSystemSpring.example.CouponSystem.beans.ClientType;
//import couponSystemSpring.example.CouponSystem.beans.Coupon;
//import couponSystemSpring.example.CouponSystem.beans.Customer;
//import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
//import couponSystemSpring.example.CouponSystem.exceptions.ErrorMessage;
//import couponSystemSpring.example.CouponSystem.security.TokenService;
//import couponSystemSpring.example.CouponSystem.services.ClientService;
//import couponSystemSpring.example.CouponSystem.services.CustomerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
////TODO: do I need to put @RequestParam on objects like coupon?
//@RestController
//@RequestMapping("api/customer")
//public class CustomerController {
//    @Autowired
//    private CustomerService customerService;
//    @Autowired
//    private TokenService tokenService;
//    @GetMapping("login/customer")
//    boolean login(@RequestHeader(value = "Authorization") UUID token,@RequestParam String email, @RequestParam String password) throws CouponSystemException {
//        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
//            throw new CouponSystemException(ErrorMessage.SECURITY_EXCEPTION_USER_NOT_ALLOWED);
//        }
//        return ((ClientService) customerService).login(email,password);
//    }
//    //TODO: do I really need getIdFromDB? for a customer?
//    @GetMapping("DB/id")
//    int getIdFromDB(@RequestParam(required = true) String email){
//        return customerService.getIdFromDB(email);
//    }
//    @GetMapping("purchase coupon/coupons")
//    void purchaseCoupon(Coupon coupon, @PathVariable int customerId) throws CouponSystemException {
//        customerService.purchaseCoupon(coupon, customerId);
//    }
//    @GetMapping("delete coupon/coupons")
//    void deleteCouponPurchased(@RequestParam Coupon coupon, @PathVariable int customerId) throws CouponSystemException {
//        customerService.deleteCouponPurchased(coupon,customerId);
//    }
//    @GetMapping("customer/coupons")
//    List<Coupon> getCustomerCoupons(@PathVariable int customerId) throws CouponSystemException {
//        return customerService.getCustomerCoupons(customerId);
//    }
//    @GetMapping("coupons/category")
//    List<Coupon> getCustomerCouponsByCategory(@RequestParam Category category, @PathVariable int customerId) throws CouponSystemException {
//        return customerService.getCustomerCouponsByCategory(category,customerId);
//    }
//    @GetMapping("coupons/max price")
//    List<Coupon> getCustomerCouponsUntilMaxPrice(@RequestParam double maxPrice, @PathVariable int customerId) throws CouponSystemException {
//        return customerService.getCustomerCouponsUntilMaxPrice(maxPrice, customerId);
//    }
//    @GetMapping("customer/details")
//    Optional<Customer> getCustomerDetails(@PathVariable int customerId) throws CouponSystemException {
//        return customerService.getCustomerDetails(customerId);
//    }
//}