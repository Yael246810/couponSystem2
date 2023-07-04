package couponSystemSpring.example.CouponSystem.JsonObjects;

import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import java.util.List;

public class CouponCustomersData {
    private Coupon coupon;
    private List<Customer> customers;

    public CouponCustomersData(Coupon coupon, List<Customer> customers) {
        this.coupon = coupon;
        this.customers = customers;
    }
    public Coupon getCoupon (){
        coupon.setCustomers(customers);
        return coupon;
    }
}
