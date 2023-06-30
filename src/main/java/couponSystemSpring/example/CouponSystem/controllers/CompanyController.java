package couponSystemSpring.example.CouponSystem.controllers;

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
@RequestMapping("api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TokenService tokenService;

    @GetMapping("login/company")
    boolean login(@RequestHeader(value = "Authorization") UUID token, @RequestParam String email, @RequestParam String password) throws CouponSystemException {
        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrorMessage.SECURITY_EXCEPTION_USER_NOT_ALLOWED);
        }
        return ((ClientService) companyService).login(email, password);
    }
        //TODO: do I really need getIdFromDB? for a customer?
//        @GetMapping("DB/id")
//        int getIdFromDB (@RequestParam String email){
//            return companyService.getIdFromDB(email);
//        }
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        void addCoupon (@RequestHeader(value = "Authorization") UUID token,@RequestBody Coupon coupon) throws CouponSystemException {
            companyService.addCoupon(coupon);
        }
        @PutMapping("coupon/update")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        void updateCoupon (@RequestHeader(value = "Authorization") UUID token,@PathVariable int couponId, @RequestBody Coupon coupon) throws CouponSystemException {
            companyService.updateCoupon(couponId, coupon);
        }
        @DeleteMapping("coupon/delete")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        void deleteCoupon (@RequestHeader(value = "Authorization") UUID token,@RequestBody Coupon coupon) throws CouponSystemException {
            companyService.deleteCoupon(coupon);
        }
        @GetMapping("company/{companyId}/coupons")
        List<Coupon> getCompanyCoupons (@RequestHeader(value = "Authorization") UUID token,@PathVariable int companyId) throws CouponSystemException {
            return companyService.getCompanyCoupons(companyId);
        }
        @GetMapping("company/coupons/category")
        List<Coupon> getCompanyCouponsByCategory (@RequestHeader(value = "Authorization") UUID token,@RequestParam Category category,@PathVariable int companyId) throws
        CouponSystemException {
            return companyService.getCompanyCouponsByCategory(category, companyId);
        }
        @GetMapping("company/coupons/max price")
        List<Coupon> getCompanyCouponsByMaxPrice (@RequestHeader(value = "Authorization") UUID token,@RequestParam double maxPrice, @PathVariable int companyId) throws
        CouponSystemException {
            return companyService.getCompanyCouponsByMaxPrice(maxPrice, companyId);
        }
        @GetMapping("company/details")
        Company getCompanyDetails (@RequestHeader(value = "Authorization") UUID token,@PathVariable int companyId) throws CouponSystemException {
            return companyService.getCompanyDetails(companyId);
        }
    }
