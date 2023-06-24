package couponSystemSpring.example.CouponSystem.controllers;

import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.Company;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.services.ClientService;
import couponSystemSpring.example.CouponSystem.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("login/company")
    boolean login(@RequestParam String email, @RequestParam String password) {
        return ((ClientService) companyService).login(email, password);
    }
    //TODO: do I really need getIdFromDB? for a customer?
    @GetMapping("DB/id")
    int getIdFromDB(@RequestParam String email) {
        return companyService.getIdFromDB(email);
    }
    @GetMapping("add coupon/coupons")
    void addCoupon(@RequestParam Coupon coupon) throws CouponSystemException {
        companyService.addCoupon(coupon);
    }
    @GetMapping("update coupon/coupons")
    void updateCoupon(@PathVariable int couponId, @RequestParam Coupon coupon) throws CouponSystemException {
        companyService.updateCoupon(couponId,coupon);
    }
    @GetMapping("delete coupon/coupons")
    void deleteCoupon(@RequestParam Coupon coupon) throws CouponSystemException {
        companyService.deleteCoupon(coupon);
    }
    @GetMapping("company/coupons")
    List<Coupon> getCompanyCoupons(@PathVariable int companyId) throws CouponSystemException {
        return companyService.getCompanyCoupons(companyId);
    }
    @GetMapping("company/coupons by category")
    List<Coupon> getCompanyCouponsByCategory(@RequestParam Category category, @PathVariable int companyId) throws CouponSystemException {
        return companyService.getCompanyCouponsByCategory(category,companyId);
    }
    @GetMapping("company/coupons by max price")
    List<Coupon> getCompanyCouponsByMaxPrice(@RequestParam double maxPrice, @PathVariable int companyId) throws CouponSystemException {
        return companyService.getCompanyCouponsByMaxPrice(maxPrice, companyId);
    }
    @GetMapping("company/details")
    Company getCompanyDetails(@PathVariable int companyId) throws CouponSystemException {
        return companyService.getCompanyDetails(companyId);
    }
}
