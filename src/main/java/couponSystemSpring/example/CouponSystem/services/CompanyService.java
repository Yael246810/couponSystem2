package couponSystemSpring.example.CouponSystem.services;

import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.Company;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;

import java.util.List;

public interface CompanyService {
    int getIdFromDB(String email);
    void addCoupon(Coupon coupon) throws CouponSystemException;
    void updateCoupon (int couponId,Coupon coupon) throws CouponSystemException;
    void deleteCoupon(Coupon coupon) throws CouponSystemException;
    List<Coupon> getCompanyCoupons(int companyId) throws CouponSystemException;
    List<Coupon> getCompanyCouponsByCategory(Category category, int companyId) throws CouponSystemException;
    List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice,int companyId) throws CouponSystemException;
    Company getCompanyDetails(int companyId) throws CouponSystemException;
}
