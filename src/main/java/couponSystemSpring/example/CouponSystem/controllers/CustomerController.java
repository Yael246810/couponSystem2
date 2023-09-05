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
@CrossOrigin
@RequestMapping("api/customers")
public class CustomerController extends BaseController{
    @Autowired
    private CustomerService customerService;

    @PostMapping("{customerId}/coupons/{couponId}/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    Coupon purchaseCoupon(@RequestHeader(value = "Authorization") String token,@PathVariable Long couponId, @PathVariable int customerId) throws Exception {
        validateToken(token);
        return customerService.purchaseCoupon(couponId, customerId);
    }
    @DeleteMapping("{customerId}/coupons/{couponId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCouponPurchased(@RequestHeader(value = "Authorization") String token,@PathVariable Long couponId, @PathVariable int customerId) throws CouponSystemException {
        validateToken(token);
        customerService.deleteCouponPurchased(couponId,customerId);
    }
    @GetMapping("{customerId}/coupons")
    List<Coupon> getCustomerCoupons(@RequestHeader(value = "Authorization") String token,@PathVariable int customerId) throws Exception {
        validateToken(token);
        return customerService.getCustomerCoupons(customerId);
    }
    @GetMapping("{customerId}/coupons/category")
    List<Coupon> getCustomerCouponsByCategory(@RequestHeader(value = "Authorization") String token,@PathVariable int customerId,@RequestParam("val") Category category) throws Exception {
        validateToken(token);
        return customerService.getCustomerCouponsByCategory(category,customerId);
    }
    @GetMapping("{customerId}/coupons/price")
    List<Coupon> getCustomerCouponsUntilPrice(@RequestHeader(value = "Authorization") String token,@PathVariable int customerId,@RequestParam("max") double maxPrice) throws CouponSystemException {
        validateToken(token);
        return customerService.getCustomerCouponsUntilPrice(customerId, maxPrice);
    }
    @GetMapping("{customerId}/details")
    Optional<Customer> getCustomerDetails(@RequestHeader(value = "Authorization") String token,@PathVariable int customerId) throws CouponSystemException {
        validateToken(token);
        return customerService.getCustomerDetails(customerId);
    }

    @Override
    protected ClientType getClientType() {
        return ClientType.CUSTOMER;
    }
}
