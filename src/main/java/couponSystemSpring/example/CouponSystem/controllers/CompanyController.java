//package couponSystemSpring.example.CouponSystem.controllers;
//
//import couponSystemSpring.example.CouponSystem.beans.Category;
//import couponSystemSpring.example.CouponSystem.beans.ClientType;
//import couponSystemSpring.example.CouponSystem.beans.Company;
//import couponSystemSpring.example.CouponSystem.beans.Coupon;
//import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
//import couponSystemSpring.example.CouponSystem.exceptions.ErrorMessage;
//import couponSystemSpring.example.CouponSystem.security.TokenService;
//import couponSystemSpring.example.CouponSystem.services.ClientService;
//import couponSystemSpring.example.CouponSystem.services.CompanyService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/company")
//public class CompanyController {
//    @Autowired
//    private CompanyService companyService;
//    @Autowired
//    private TokenService tokenService;
//
//    @GetMapping("login/company")
//    boolean login(@RequestHeader(value = "Authorization") UUID token, @RequestParam String email, @RequestParam String password) throws CouponSystemException {
//        if (!tokenService.isUserAllowed(token, ClientType.COMPANY)) {
//            throw new CouponSystemException(ErrorMessage.SECURITY_EXCEPTION_USER_NOT_ALLOWED);
//        }
//        return ((ClientService) companyService).login(email, password);
//    }
//        //TODO: do I really need getIdFromDB? for a customer?
//        @GetMapping("DB/id")
//        int getIdFromDB (@RequestParam String email){
//            return companyService.getIdFromDB(email);
//        }
//        @GetMapping("coupons/add coupon")
//        void addCoupon (@RequestParam Coupon coupon) throws CouponSystemException {
//            companyService.addCoupon(coupon);
//        }
//        @GetMapping("coupons/update coupon")
//        void updateCoupon ( @PathVariable int couponId, @RequestParam Coupon coupon) throws CouponSystemException {
//            companyService.updateCoupon(couponId, coupon);
//        }
//        @GetMapping("coupons/delete coupon")
//        void deleteCoupon (@RequestParam Coupon coupon) throws CouponSystemException {
//            companyService.deleteCoupon(coupon);
//        }
//        @GetMapping("company/coupons")
//        List<Coupon> getCompanyCoupons ( @PathVariable int companyId) throws CouponSystemException {
//            return companyService.getCompanyCoupons(companyId);
//        }
//        @GetMapping("company/coupons/category")
//        List<Coupon> getCompanyCouponsByCategory (@RequestParam Category category,@PathVariable int companyId) throws
//        CouponSystemException {
//            return companyService.getCompanyCouponsByCategory(category, companyId);
//        }
//        @GetMapping("company/coupons/max price")
//        List<Coupon> getCompanyCouponsByMaxPrice ( @RequestParam double maxPrice, @PathVariable int companyId) throws
//        CouponSystemException {
//            return companyService.getCompanyCouponsByMaxPrice(maxPrice, companyId);
//        }
//        @GetMapping("company/details")
//        Company getCompanyDetails ( @PathVariable int companyId) throws CouponSystemException {
//            return companyService.getCompanyDetails(companyId);
//        }
//    }
