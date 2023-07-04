package couponSystemSpring.example.CouponSystem.controllers;

import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.ClientType;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/customers")
public class CustomerController extends BaseController{
    @Autowired
    private CustomerService customerService;

    @PostMapping("{customerId}/coupons/{couponId}/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    void purchaseCoupon(@RequestHeader("Authorization") String token, @PathVariable Long couponId, @PathVariable int customerId) throws Exception {
        validateToken(token);
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

    @Override
    protected ClientType getClientType() {
        return ClientType.CUSTOMER;
    }
}
