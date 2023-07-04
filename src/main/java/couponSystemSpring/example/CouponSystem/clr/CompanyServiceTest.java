package couponSystemSpring.example.CouponSystem.clr;

import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.repositories.CouponRepository;
import couponSystemSpring.example.CouponSystem.services.AdminService;
import couponSystemSpring.example.CouponSystem.services.ClientService;
import couponSystemSpring.example.CouponSystem.services.CompanyService;
import couponSystemSpring.example.CouponSystem.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

//TODO:there is a problem with: get company coupons method - all kinds(category,maxPrice)
@Component
@Order(3)
public class CompanyServiceTest implements CommandLineRunner {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {
        // Login
        TestUtils.test("Company service - problem with the login - wrong email address");
        System.out.println(((ClientService) companyService).login("sivan@gmail.com", "1234"));
        TestUtils.test("Company service - problem with the login - wrong password");
        System.out.println(((ClientService) companyService).login("Shein@gmail.com", "1234"));
        TestUtils.test("Company service - problem with the login - wrong email address and password");
        System.out.println(((ClientService) companyService).login("Shanya@gmail.com", "1282"));
        TestUtils.test("Company service - the login succeeded");
        System.out.println(((ClientService) companyService).login("Shein@gmail.com", "1234"));
        System.out.println(((ClientService) companyService).login("Fox@gmail.com", "1234"));
        System.out.println(((ClientService) companyService).login("Intel@gmail.com", "1234"));

        System.out.println("----------------------------------------------------------------------------");

        //Add coupon
        TestUtils.test("Company service - id already exists - cannot add coupon");
        Coupon couponToAdd = new Coupon(3, adminService.getSingleCompany(3).orElseThrow(), Category.MOVIES, null, "ttt", "tta", Date.valueOf(LocalDate.now().minusDays(3)), Date.valueOf(LocalDate.now().plusWeeks(1)), 100, 90, "http");
        try {
            companyService.addCoupon(couponToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        TestUtils.test("Company service - same title of the same company - cannot add coupon");
        Coupon couponToAdd1 = new Coupon(11, adminService.getSingleCompany(3).orElseThrow(() -> new RuntimeException()), Category.MOVIES, null, "MarketPlace - 10% off on furniture", "tta", Date.valueOf(LocalDate.now().minusDays(3)), Date.valueOf(LocalDate.now().plusWeeks(1)), 100, 90, "http");
        try {
            companyService.addCoupon(couponToAdd1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        TestUtils.test("Company service - same title of the a different company - successfully added coupon");

        Coupon couponToAdd2 = new Coupon(12, adminService.getSingleCompany(4).orElseThrow(() -> new RuntimeException()), Category.MOVIES, null, "MarketPlace - 10% off on furniture", "tta", Date.valueOf(LocalDate.now().minusDays(3)), Date.valueOf(LocalDate.now().plusWeeks(1)), 100, 90.0, "http");
        companyService.addCoupon(couponToAdd2);
        System.out.println(adminService.getSingleCompany(4));
        System.out.println("---------------------------------------------------------------------------------");

        // Update coupon
        TestUtils.test("Company Service - update coupon - id does not exist");
        try {
        Coupon coupon = Coupon.builder()
                .id(123)
                .company(adminService.getSingleCompany(5678).orElseThrow())
                .amount(100)
                .price(200)
                .build();

            companyService.updateCoupon((int) coupon.getId(), coupon);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        TestUtils.test("Company Service - update coupon - id does not match, cannot change id");
        try {
            Coupon coupon1 = Coupon.builder()
                    .id(0)
                    .company(adminService.getSingleCompany(5678).orElseThrow())
                    .amount(100)
                    .price(200)
                    .build();
            coupon1.setId(2);

            companyService.updateCoupon((int) coupon1.getId(), coupon1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        TestUtils.test("Company Service - update coupon - succeeded");
        Optional<Coupon> coupon3 = couponRepository.findById(11L);
        coupon3.get().setAmount(250);
        coupon3.get().setPrice(10);
        companyService.updateCoupon((int) coupon3.get().getId(), coupon3.get());
        System.out.println("this is the updated coupon "+coupon3);
        System.out.println("----------------------------------------------------");

        // Delete coupon
        TestUtils.test("Company Service - delete coupon - id does not exist");
        try {
            Coupon couponToDelete = Coupon.builder()
                    .id(123)
                    .company(adminService.getSingleCompany(8).orElseThrow())
                    .amount(100)
                    .price(200)
                    .build();

            companyService.deleteCoupon(couponToDelete.getId());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        TestUtils.test("Company Service - delete coupon - succeeded");
        Coupon couponToDelete1 = Coupon.builder()
                .id(1)
                .title("mama this coupon is for you")
                .description("hello Im coupon")
                .startDate(Date.valueOf(LocalDate.now().minusDays(3)))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(1)))
                .category(Category.MOVIES)
                .image("http")
                .company(adminService.getSingleCompany(2).orElseThrow())
                .amount(100)
                .price(200)
                .build();
        companyService.deleteCoupon(couponToDelete1.getId());
        System.out.println("this is the deleted coupon: "+couponToDelete1);
        System.out.println("---------------------------------------------------------");

        // get company coupons
        TestUtils.test("Company Service - get company coupons - succeeded");
        System.out.println(companyService.getCompanyCoupons(4));
        System.out.println("--------------------------------------------------------------");

        // get company coupons by category
        TestUtils.test("Company Service - get company coupons by category - succeeded");
        System.out.println(companyService.getCompanyCouponsByCategory(Category.CLOTHING, 2));
        System.out.println("---------------------------------------------------------------");

        // get company coupons by max price
        TestUtils.test("Company Service - get company coupons by max price - succeeded");
        System.out.println(companyService.getCompanyCouponsByMaxPrice(300,10));
        System.out.println("--------------------------------------------------------------");

        // get company details
        TestUtils.test("Company Service - get company details - id does not exist");
        try{
            companyService.getCompanyDetails(32);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        TestUtils.test("Company Service - get company details - company didn't login");
        try {
            companyService.getCompanyDetails(1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        TestUtils.test("Company Service - get company details - succeeded");
        companyService.getCompanyDetails(2);
        System.out.println(companyService.getCompanyDetails(2));
    }
}

