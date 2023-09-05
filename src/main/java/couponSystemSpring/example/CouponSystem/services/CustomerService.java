package couponSystemSpring.example.CouponSystem.services;

import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Coupon purchaseCoupon(Long couponId, int customerId) throws Exception;
    void deleteCouponPurchased(Long couponId, int customerId) throws CouponSystemException;
    List<Coupon> getCustomerCoupons(int customerId) throws Exception;
    List<Coupon>getCustomerCouponsByCategory(Category category, int customerId) throws Exception;
    List<Coupon> getCustomerCouponsUntilPrice(int customerId,double max) throws CouponSystemException;
    Optional<Customer> getCustomerDetails(int customerId) throws CouponSystemException;
}
