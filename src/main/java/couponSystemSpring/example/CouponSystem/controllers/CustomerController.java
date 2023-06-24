package couponSystemSpring.example.CouponSystem.controllers;

import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.services.ClientService;
import couponSystemSpring.example.CouponSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//TODO: do I need to put @RequestParam on objects like coupon?
@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("login/customer")
    boolean login(String email, String password){
        return ((ClientService) customerService).login(email,password);
    }
    //TODO: do I really need getIdFromDB? for a customer?
    @GetMapping("DB/id")
    int getIdFromDB(@RequestParam(required = true) String email){
        return customerService.getIdFromDB(email);
    }
    @GetMapping("purchase coupon/coupons")
    void purchaseCoupon(Coupon coupon, @PathVariable int customerId) throws CouponSystemException {
        customerService.purchaseCoupon(coupon, customerId);
    }
    @GetMapping("delete coupon/coupons")
    void deleteCouponPurchased(@RequestParam Coupon coupon, @PathVariable int customerId) throws CouponSystemException {
        customerService.deleteCouponPurchased(coupon,customerId);
    }
    @GetMapping("customer/coupons")
    List<Coupon> getCustomerCoupons(@PathVariable int customerId) throws CouponSystemException {
        return customerService.getCustomerCoupons(customerId);
    }
    @GetMapping("coupons/category")
    List<Coupon> getCustomerCouponsByCategory(@RequestParam Category category, @PathVariable int customerId) throws CouponSystemException {
        return customerService.getCustomerCouponsByCategory(category,customerId);
    }
    @GetMapping("coupons/max price")
    List<Coupon> getCustomerCouponsUntilMaxPrice(@RequestParam double maxPrice, @PathVariable int customerId) throws CouponSystemException {
        return customerService.getCustomerCouponsUntilMaxPrice(maxPrice, customerId);
    }
    @GetMapping("customer/details")
    Customer getCustomerDetails(@PathVariable int customerId) throws CouponSystemException {
        return customerService.getCustomerDetails(customerId);
    }
}
