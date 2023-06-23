package couponSystemSpring.example.CouponSystem.services;

import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;

import java.util.List;
public interface CustomerService {
    void purchaseCoupon(Coupon coupon, int customerId) throws CouponSystemException;
    void deleteCouponPurchased(Coupon coupon, int customerId) throws CouponSystemException;
    List<Coupon> getCustomerCoupons(int customerId) throws CouponSystemException;
    List<Coupon>getCustomerCouponsByCategory(Category category, int customerId) throws CouponSystemException;
    List<Coupon>getCustomerCouponsUntilMaxPrice(double maxPrice,int customerId) throws CouponSystemException;
    Customer getCustomerDetails(int customerId) throws CouponSystemException;
    int getIdFromDB(String email);
}
