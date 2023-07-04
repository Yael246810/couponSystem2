package couponSystemSpring.example.CouponSystem.controllers;

import couponSystemSpring.example.CouponSystem.JsonObjects.CouponCompanyData;
import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.ClientType;
import couponSystemSpring.example.CouponSystem.beans.Company;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.exceptions.ErrorMessage;
import couponSystemSpring.example.CouponSystem.security.TokenService;
import couponSystemSpring.example.CouponSystem.services.ClientService;
import couponSystemSpring.example.CouponSystem.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TokenService tokenService;

//    @PostMapping("login")
//    boolean login(@RequestHeader("Authorization") UUID token, @RequestParam String email, @RequestParam String password) throws CouponSystemException {
//        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
//            throw new CouponSystemException(ErrorMessage.SECURITY_EXCEPTION_USER_NOT_ALLOWED);
//        }
//        return ((ClientService) companyService).login(email, password);
//    }
        @PostMapping("coupon")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        void addCoupon (@RequestHeader( "Authorization") String token, @RequestBody CouponCompanyData couponCompanyData) throws CouponSystemException {
            companyService.addCoupon(couponCompanyData.getCoupon());
        }
        @PutMapping("coupon")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        void updateCoupon (@RequestHeader("Authorization") String token, @RequestBody CouponCompanyData couponCompanyData) throws CouponSystemException {
            Coupon coupon = couponCompanyData.getCoupon();
            companyService.updateCoupon((int) coupon.getId(),coupon);
        }
        @DeleteMapping("{couponId}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        void deleteCoupon (@RequestHeader("Authorization") String token,@PathVariable long couponId) throws CouponSystemException {
            companyService.deleteCoupon(couponId);
        }
        @GetMapping("{companyId}/coupons")
        List<Coupon> getCompanyCoupons (@RequestHeader("Authorization") String token,@PathVariable int companyId) throws CouponSystemException {
            return companyService.getCompanyCoupons(companyId);
        }
        @GetMapping("{companyId}/coupons/category")
        List<Coupon> getCompanyCouponsByCategory (@RequestHeader("Authorization") String token,@PathVariable int companyId, @RequestParam("val") Category category) throws
        CouponSystemException {
            return companyService.getCompanyCouponsByCategory(category, companyId);
        }
        @GetMapping("{companyId}/coupons/price")
        List<Coupon> getCompanyCouponsByMaxPrice (@RequestHeader("Authorization") String token,@RequestParam("max") double maxPrice, @PathVariable int companyId) throws
        CouponSystemException {
            return companyService.getCompanyCouponsByMaxPrice(maxPrice, companyId);
        }
        @GetMapping("{companyId}/details")
        Company getCompanyDetails (@RequestHeader("Authorization") String token,@PathVariable int companyId) throws CouponSystemException {
            return companyService.getCompanyDetails(companyId);
        }
    }
