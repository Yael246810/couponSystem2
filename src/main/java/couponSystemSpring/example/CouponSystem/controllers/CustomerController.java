package couponSystemSpring.example.CouponSystem.controllers;

import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.ClientType;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.exceptions.ErrorMessage;
import couponSystemSpring.example.CouponSystem.security.TokenService;
import couponSystemSpring.example.CouponSystem.services.ClientService;
import couponSystemSpring.example.CouponSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TokenService tokenService;
    @PostMapping("login/customer")
    boolean login(@RequestHeader(value = "Authorization") UUID token,@RequestParam String email, @RequestParam String password) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrorMessage.SECURITY_EXCEPTION_USER_NOT_ALLOWED);
        }
        return ((ClientService) customerService).login(email,password);
    }
    @PostMapping("purchase/coupon/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    void purchaseCoupon(@RequestHeader(value = "Authorization") UUID token,@RequestBody Coupon coupon, @PathVariable int customerId) throws Exception {
        customerService.purchaseCoupon(coupon, customerId);
    }
    @DeleteMapping("delete/coupon/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCouponPurchased(@RequestHeader(value = "Authorization") UUID token,@RequestBody Coupon coupon, @PathVariable int customerId) throws CouponSystemException {
        customerService.deleteCouponPurchased(coupon,customerId);
    }
    @GetMapping("customer/{customerId}/coupons")
    List<Coupon> getCustomerCoupons(@RequestHeader(value = "Authorization") UUID token,@PathVariable int customerId) throws Exception {
        return customerService.getCustomerCoupons(customerId);
    }
    @GetMapping("customer/coupons/category")
    List<Coupon> getCustomerCouponsByCategory(@RequestHeader(value = "Authorization") UUID token,@RequestParam Category category, @PathVariable int customerId) throws Exception {
        return customerService.getCustomerCouponsByCategory(category,customerId);
    }
    @GetMapping("customer/coupons/max price")
    List<Coupon> getCustomerCouponsUntilMaxPrice(@RequestHeader(value = "Authorization") UUID token,@RequestParam double maxPrice, @PathVariable int customerId) throws CouponSystemException {
        return customerService.getCustomerCouponsUntilMaxPrice(maxPrice, customerId);
    }
    @GetMapping("customer/details")
    Optional<Customer> getCustomerDetails(@RequestHeader(value = "Authorization") UUID token,@PathVariable int customerId) throws CouponSystemException {
        return customerService.getCustomerDetails(customerId);
    }
}
