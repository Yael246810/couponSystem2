package couponSystemSpring.example.CouponSystem.controllers;

import couponSystemSpring.example.CouponSystem.JsonObjects.CouponCompanyData;
import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.ClientType;
import couponSystemSpring.example.CouponSystem.beans.Company;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/companies")
public class CompanyController extends BaseController{
    @Autowired
    private CompanyService companyService;

        @PostMapping("coupon")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        void addCoupon (@RequestHeader( "Authorization") String token, @RequestBody CouponCompanyData couponCompanyData) throws CouponSystemException {
           validateToken(token);
            companyService.addCoupon(couponCompanyData.getCoupon());
        }
        @PutMapping("coupon")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        void updateCoupon (@RequestHeader("Authorization") String token, @RequestBody CouponCompanyData couponCompanyData) throws CouponSystemException {
            validateToken(token);
            Coupon coupon = couponCompanyData.getCoupon();
            companyService.updateCoupon((int) coupon.getId(),coupon);
        }
        @DeleteMapping("{couponId}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        void deleteCoupon (@RequestHeader("Authorization") String token,@PathVariable long couponId) throws CouponSystemException {
            validateToken(token);
            companyService.deleteCoupon(couponId);
        }
        @GetMapping("{companyId}/coupons")
        List<Coupon> getCompanyCoupons (@RequestHeader("Authorization") String token,@PathVariable int companyId) throws CouponSystemException {
            validateToken(token);
            return companyService.getCompanyCoupons(companyId);
        }
        @GetMapping("{companyId}/coupons/category")
        List<Coupon> getCompanyCouponsByCategory (@RequestHeader("Authorization") String token,@PathVariable int companyId, @RequestParam("val") Category category) throws
        CouponSystemException {
            validateToken(token);
            return companyService.getCompanyCouponsByCategory(category, companyId);
        }
        @GetMapping("{companyId}/coupons/price")
        List<Coupon> getCompanyCouponsByMaxPrice (@RequestHeader("Authorization") String token,@RequestParam("max") double maxPrice, @PathVariable int companyId) throws
        CouponSystemException {
            validateToken(token);
            return companyService.getCompanyCouponsByMaxPrice(maxPrice, companyId);
        }
        @GetMapping("{companyId}/details")
        Company getCompanyDetails (@RequestHeader("Authorization") String token,@PathVariable int companyId) throws CouponSystemException {
            validateToken(token);
            return companyService.getCompanyDetails(companyId);
        }

    @Override
    protected ClientType getClientType() {
        return ClientType.COMPANY;
    }
}
