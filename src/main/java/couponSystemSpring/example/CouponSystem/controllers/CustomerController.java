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
//TODO: there is a problem with the query of max price in here... after I will fix it, it supposed to work

@RestController
@RequestMapping("api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TokenService tokenService;
//    @PostMapping("login")
//    boolean login(@RequestHeader("Authorization") UUID token, @RequestParam String email, @RequestParam String password) throws CouponSystemException {
//        if (!tokenService.isUserAllowed(token, ClientType.CUSTOMER)) {
//            throw new CouponSystemException(ErrorMessage.SECURITY_EXCEPTION_USER_NOT_ALLOWED);
//        }
//        return ((ClientService) customerService).login(email,password);
//    }
    @PostMapping("{customerId}/coupons/{couponId}/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    void purchaseCoupon(@RequestHeader("Authorization") String token, @PathVariable Long couponId, @PathVariable int customerId) throws Exception {
        customerService.purchaseCoupon(couponId, customerId);
    }
    @DeleteMapping("{customerId}/coupons/{couponId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCouponPurchased(@RequestHeader("Authorization") String token,@PathVariable Long couponId, @PathVariable int customerId) throws CouponSystemException {
        customerService.deleteCouponPurchased(couponId,customerId);
    }
    @GetMapping("{customerId}/coupons")
    List<Coupon> getCustomerCoupons(@RequestHeader("Authorization") String token,@PathVariable int customerId) throws Exception {
        return customerService.getCustomerCoupons(customerId);
    }
    @GetMapping("{customerId}/coupons/category")
    List<Coupon> getCustomerCouponsByCategory(@RequestHeader("Authorization") String token,@RequestParam("val") Category category, @PathVariable int customerId) throws Exception {
        return customerService.getCustomerCouponsByCategory(category,customerId);
    }
    @GetMapping("{customerId}/coupons/price")
    List<Coupon> getCustomerCouponsUntilPrice(@RequestHeader("Authorization") String token, @RequestParam("max") double max, @PathVariable int customerId) throws CouponSystemException {
        return customerService.getCustomerCouponsUntilPrice(max, customerId);
    }
    @GetMapping("{customerId}/details")
    Optional<Customer> getCustomerDetails(@RequestHeader("Authorization") String token,@PathVariable int customerId) throws CouponSystemException {
        return customerService.getCustomerDetails(customerId);
    }
}
