package couponSystemSpring.example.CouponSystem.clr;

import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.services.AdminService;
import couponSystemSpring.example.CouponSystem.services.ClientService;
import couponSystemSpring.example.CouponSystem.services.CustomerService;
import couponSystemSpring.example.CouponSystem.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
//TODO: there are problems with: get customer coupons
@Component
@Order(4)
public class CustomerServiceTest implements CommandLineRunner {
    @Autowired
    CustomerService customerService;
    @Autowired
    AdminService adminService;

    @Override
    public void run(String... args) throws Exception {
        // Login

        TestUtils.test("Customer Service - problem with the login - wrong email address");
        System.out.println(((ClientService) customerService).login("sivan@gmail.com", "1234"));
        TestUtils.test("Customer Service - problem with the login - wrong password");
        System.out.println(((ClientService) customerService).login("Roni@gmail.com", "1212"));
        TestUtils.test("Customer Service - problem with the login - wrong email address and password");
        System.out.println(((ClientService) customerService).login("Shanya@gmail.com", "1282"));
        TestUtils.test("Customer Service - login succeeded");
        System.out.println(((ClientService) customerService).login("Roni@gmail.com", "1234"));
        System.out.println(((ClientService) customerService).login("Noam@gmail.com", "1234"));
        System.out.println("----------------------------------------------------------------------------");

        // Purchase coupon
        TestUtils.test("Customer Service - purchase coupon - customer already has the coupon");
        Coupon coupon = new Coupon(2,adminService.getSingleCompany(2).orElseThrow(()->new RuntimeException()), Category.CLOTHING,null,"Shein - 20% off on all orders","Get 20% off on all orders over 200 shekels", Date.valueOf(LocalDate.now().minusDays(3)),Date.valueOf(LocalDate.now().plusWeeks(1)),500,50,"https://media.giphy.com/media/l0HlK8rC1z6vSMdKM/giphy.gif");
        try{
            customerService.purchaseCoupon(coupon.getId(),1);
            customerService.purchaseCoupon(coupon.getId(),1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        TestUtils.test("Customer Service - purchase coupon - amount = 0");
        Coupon coupon1 = new Coupon(10,adminService.getSingleCompany(10).orElseThrow(()->new RuntimeException()), Category.ELECTRONICS,null,"Intel - Get 10% off on all processors","Upgrade your PC with Intel Core i7 processors and get 10% discount", Date.valueOf(LocalDate.now().minusDays(3)),Date.valueOf(LocalDate.now().plusWeeks(1)),0,200,"https://media.giphy.com/media/5VKbvrjxpVJCM/giphy.gif");
        try {
            customerService.purchaseCoupon(coupon1.getId(),2);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        TestUtils.test("Customer Service - purchase coupon - coupon was expired");
        Coupon coupon2 = new Coupon(1,adminService.getSingleCompany(8).orElseThrow(()->new RuntimeException()), Category.FOOD,null,"Coca Cola - Buy One Get One Free","Buy one Coca Cola and get another one for free", Date.valueOf(LocalDate.now().minusDays(3)),Date.valueOf(LocalDate.now().plusWeeks(1)),1000,10,"https://media.giphy.com/media/9M0m0Ub8rCkEUZaSZD/giphy.gif");
        try {
            customerService.purchaseCoupon(coupon2.getId(),4);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        TestUtils.test("Customer Service - purchase coupon - coupon was purchased successfully");
        Coupon coupon3 = new Coupon(6,adminService.getSingleCompany(6).orElseThrow(()->new RuntimeException()),Category.CLOTHING,null,"Castro - 10% off on all summer collections","Get 10% off on all summer collections over $100",Date.valueOf(LocalDate.now().minusDays(3)),Date.valueOf(LocalDate.now().plusWeeks(1)),500,100,"https://media.giphy.com/media/3o7abHxRc5CYg1qmYQ/giphy.gif");
        customerService.purchaseCoupon(coupon3.getId(),2);
        System.out.println("------------------------------------------------------------------------------------------");

        // Delete customer coupon
        TestUtils.test("Customer Service - delete coupon for customer - customer id does not exist");
        try {
            customerService.deleteCouponPurchased(coupon2.getId(), 123);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        TestUtils.test("Customer Service - delete coupon for customer - customer doesn't have this coupon");
        try {
            customerService.deleteCouponPurchased(coupon.getId(), 7);
        }catch (Exception e){
            e.getMessage();
        }

        TestUtils.test("Customer Service - delete coupon for customer - succeeded");
        customerService.deleteCouponPurchased(coupon3.getId(), 2);
        System.out.println("-----------------------------------------------------------------------------------------");

       //get customer coupons
        TestUtils.test("Customer Service - get customer coupons - succeeded");
        System.out.println(customerService.getCustomerCoupons(2)); //somehow it returns null and not the customer coupons
        System.out.println("------------------------------------------------------------------------------------------");

        //get customer coupons by category
        TestUtils.test("Customer Service - get customer coupons by category - succeeded");
        System.out.println(customerService.getCustomerCouponsByCategory(Category.CLOTHING,2));
        System.out.println("-------------------------------------------------------------------------------------------");

        // get customer coupons by max price
        TestUtils.test("Customer Service - get customer coupons by max price - succeeded");
        System.out.println(customerService.getCustomerCouponsUntilPrice(2,100.0));

        // get customer details
        TestUtils.test("Customer Service - get customer details - succeeded");
        System.out.println(((ClientService) customerService).login("Noam@gmail.com", "1234"));
        System.out.println(customerService.getCustomerDetails(2));
    }
}
