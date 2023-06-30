package couponSystemSpring.example.CouponSystem.clr;

import couponSystemSpring.example.CouponSystem.beans.*;
import couponSystemSpring.example.CouponSystem.repositories.CompanyRepository;
import couponSystemSpring.example.CouponSystem.repositories.CouponRepository;
import couponSystemSpring.example.CouponSystem.repositories.CustomerRepository;
import couponSystemSpring.example.CouponSystem.repositories.UserRepository;
import couponSystemSpring.example.CouponSystem.utils.Art;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@Order(1)
public class Init implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {

//        System.out.println(Art.CATEGORIES);
//        for (Category category : Category.values()) {
//            String name = category.name();
//            categoryDao.addCategory(name);
//        }
//        Arrays.stream(Category.values()).forEach(category -> System.out.println(category.getDBValue()+"\t"+category.name()));
//    }
//
//    //private static void loadCustomers() {
        System.out.println(Art.CUSTOMERS);
        Customer c1 = Customer.builder()
                .firstName("Roni")
                .lastName("Barak")
                .email("Roni@gmail.com")
                .password("1234")
                .id(1).build();
        Customer c2 = Customer.builder()
                .firstName("Noam")
                .lastName("Menachem")
                .email("Noam@gmail.com")
                .password("1234")
                .id(2).build();
        Customer c3 = new Customer("Revital", "Shemi", "Revital@gmail.com", "1234");
        Customer c4 = new Customer("Yamit", "Herut", "Yamit@gmail.com", "1234");
        Customer c5 = new Customer("Grace", "Jelen", "Grace@gmail.com", "1234");
        Customer c6 = new Customer("Aharon", "Lev", "Aharon@gmail.com", "1234");
        Customer c7 = new Customer("Neomi", "Lilach", "Neomi@gmail.com", "1234");
        Customer c8 = new Customer("Adi", "Shvat", "Adi@gmail.com", "1234");
        Customer c9 = new Customer("Rachel", "Hart", "Rachel@gmail.com", "1234");
        Customer c10 = new Customer("Noga", "Barak", "Noga@gmail.com", "1234");
        List<Customer> customers = List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);
        customerRepository.saveAll(customers);
        customerRepository.findAll().forEach(System.out::println);

        //private static void loadCompanies() {
        System.out.println(Art.COMPANIES);
        Company company1 = new Company("Coca cola", "cocaCola@gmail.com", "1234");
        Company company2 = new Company("Shein", "Shein@gmail.com", "1234");
        Company company3 = new Company("MarketPlace", "MarketPlace@gmail.com", "1234");
        Company company4 = new Company("Fox", "Fox@gmail.com", "1234");
        Company company5 = new Company("Zimmer", "Zimmer@gmail.com", "1234");
        Company company6 = new Company("Castro", "Castro@gmail.com", "1234");
        Company company7 = new Company("MoonActive", "MoonActive@gmail.com", "1234");
        Company company8 = new Company("McDonald", "McDonald@gmail.com", "1234");
        Company company9 = new Company("Ivory", "Ivory@gmail.com", "1234");
        Company company10 = new Company("Intel", "Intel@gmail.com", "1234");
        List<Company> companies = List.of(company1, company2, company3, company4, company5, company6, company7, company8, company9, company10);
        companyRepository.saveAll(companies);
        companyRepository.findAll().forEach(System.out::println);

        //private static void loadCoupons() {
        System.out.println(Art.COUPONS);
        Date startDate = Date.valueOf(LocalDate.now());
        Date endDate = Date.valueOf(LocalDate.now().plusWeeks(1));

        Coupon coupon1 = Coupon.builder()
                .company(company8)
                .category(Category.FOOD)
                .title("Big Mac Meal Deal")
                .description("Get a Big Mac meal for only 20 shekels")
                .startDate(startDate)
                .endDate(endDate)
                .amount(200)
                .price(20)
                .image("https://media4.giphy.com/media/Qtj9HpyAyRvQclo3Ka/giphy.gif?cid=ecf05e47olmlzj5ltm8ixks3ka9p76i3ma49w61pw7786pai&rid=giphy.gif&ct=g")
                .id(0).build();
        Coupon coupon2 = Coupon.builder()
                .company(company2)
                .category(Category.CLOTHING)
                .title("Shein - 20% off on all orders")
                .description("Get 20% off on all orders over 200 shekels")
                .startDate(startDate)
                .endDate(endDate)
                .amount(500)
                .price(50)
                .image("https://media.giphy.com/media/l0HlK8rC1z6vSMdKM/giphy.gif")
                .id(0).build();
        Coupon coupon3 = Coupon.builder()
                .company(company3)
                .category(Category.HOME)
                .title("MarketPlace - 10% off on furniture")
                .description("Get 10% on all furniture items over $100")
                .startDate(startDate)
                .endDate(endDate)
                .amount(300)
                .price(100)
                .image("https://media.giphy.com/media/3o6Zt6MLD9uODMj6zC/giphy.gif")
                .id(0).build();
        Coupon coupon4 = Coupon.builder()
                .company(company4)
                .category(Category.MOVIES)
                .title("Fox - 20% off on all movie tickets")
                .description("Get 20% off on all movie tickets")
                .startDate(startDate)
                .endDate(endDate)
                .amount(100)
                .price(20)
                .id(0)
                .image("https://media.giphy.com/media/3o7qDVX9sN0f0n2Il6/giphy.gif")
                .build();
        Coupon coupon5 = Coupon.builder()
                .company(company5)
                .category(Category.TRAVEL)
                .title("Zimmer - $50 off on all hotel bookings")
                .description("Get $50 off on all hotel bookings over $500")
                .startDate(startDate)
                .endDate(endDate)
                .amount(200)
                .price(500.00)
                .id(0)
                .image("https://media.giphy.com/media/3o7btRJdNZ5h5A9jDO/giphy.gif")
                .build();
        Coupon coupon6 = Coupon.builder()
                .company(company6)
                .category(Category.CLOTHING)
                .title("Castro - 10% off on all summer collections")
                .description("Get 10% off on all summer collections over $100")
                .startDate(startDate)
                .endDate(endDate)
                .amount(500)
                .price(100.00)
                .id(0)
                .image("https://media.giphy.com/media/3o7abHxRc5CYg1qmYQ/giphy.gif")
                .build();
        Coupon coupon7 = Coupon.builder()
                .company(company7)
                .category(Category.GAMES)
                .title("MoonActive - 100 free spins on Coin Master")
                .description("Get 100 free spins on Coin Master when you purchase $10 in coins")
                .startDate(startDate)
                .endDate(endDate)
                .amount(1000)
                .price(10.00)
                .id(0)
                .image("https://media.giphy.com/media/l3fZzLWxRD8ypvM3C/giphy.gif")
                .build();
        Coupon coupon8 = Coupon.builder()
                .company(company10)
                .category(Category.FOOD)
                .title("Coca Cola - Buy One Get One Free")
                .description("Buy one Coca Cola and get another one for free")
                .startDate(Date.valueOf(LocalDate.now().minusWeeks(1)))
                .endDate(Date.valueOf(LocalDate.now().minusDays(1)))
                .amount(1000)
                .price(10.00)
                .id(0)
                .image("https://media.giphy.com/media/9M0m0Ub8rCkEUZaSZD/giphy.gif")
                .build();
        Coupon coupon9 = Coupon.builder()
                .company(company9)
                .category(Category.ELECTRONICS)
                .title("Ivory - 20% off on all laptops and desktops")
                .description("Get 20% discount on all Ivory laptops and desktops")
                .startDate(startDate)
                .endDate(endDate)
                .amount(100)
                .price(40.00)
                .id(0)
                .image("https://media.giphy.com/media/3oKIPfRg8aJW77Nv3q/giphy.gif")
                .build();
        Coupon coupon10 = Coupon.builder()
                .company(company10)
                .category(Category.ELECTRONICS)
                .title("Intel - Get 10% off on all processors")
                .description("Upgrade your PC with Intel Core i7 processors and get 10% discount")
                .startDate(startDate)
                .endDate(endDate)
                .amount(50)
                .price(45.00)
                .id(0)
                .image("https://media.giphy.com/media/5VKbvrjxpVJCM/giphy.gif")
                .build();
        List<Coupon> coupons = List.of(coupon1, coupon2, coupon3, coupon4, coupon5, coupon6, coupon7, coupon8, coupon9, coupon10);
        couponRepository.saveAll(coupons);
        couponRepository.findAll().forEach(System.out::println);

        User u1 = User.builder()
                .id(0)
                .email("admin@admin.com")
                .password("admin")
                .type(ClientType.ADMIN)
                .build();

        User u2 = User.builder()
                .id(company4.getId())
                .email("Fox@gmail.com")
                .password("1234")
                .type(ClientType.COMPANY)
                .build();

        User u3 = User.builder()
                .id(c1.getId())
                .email("Roni@gmail.com")
                .password("1234")
                .type(ClientType.CUSTOMER)
                .build();

        User u4 = User.builder()
                .id(c2.getId())
                .email("Noam@gmail.com")
                .password("1234")
                .type(ClientType.CUSTOMER)
                .build();

        userRepository.saveAll(List.of(u1,u2,u3,u4));
        userRepository.findAll().forEach(System.out::println);
    }
}





