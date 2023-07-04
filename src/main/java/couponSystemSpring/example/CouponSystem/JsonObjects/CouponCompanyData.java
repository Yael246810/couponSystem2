package couponSystemSpring.example.CouponSystem.JsonObjects;

import couponSystemSpring.example.CouponSystem.beans.Company;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
public class CouponCompanyData {
    private Coupon coupon;
    private Company company;

    public CouponCompanyData(Coupon coupon, Company company) {
        this.coupon = coupon;
        this.company = company;
    }

    public Coupon getCoupon (){
        coupon.setCompany(company);
        return coupon;
    }
}
