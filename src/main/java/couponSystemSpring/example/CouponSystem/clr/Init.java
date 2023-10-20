package couponSystemSpring.example.CouponSystem.clr;

import couponSystemSpring.example.CouponSystem.beans.*;
import couponSystemSpring.example.CouponSystem.repositories.CompanyRepository;
import couponSystemSpring.example.CouponSystem.repositories.CouponRepository;
import couponSystemSpring.example.CouponSystem.repositories.CustomerRepository;
import couponSystemSpring.example.CouponSystem.repositories.UserRepository;
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

        User u0 = User.builder()
                .email("admin@admin.com")
                .password("admin")
                .type(ClientType.ADMIN)
                .build();

        User u1 = User.builder()
                .email("cocaCola@gmail.com")
                .password("1234")
                .type(ClientType.COMPANY)
                .build();

        User u2 = User.builder()
                .email("Shein@gmail.com")
                .password("1234")
                .type(ClientType.COMPANY)
                .build();

        User u3 = User.builder()
                .email("MarketPlace@gmail.com")
                .password("1234")
                .type(ClientType.COMPANY)
                .build();

        User u4 = User.builder()
                .email("Fox@gmail.com")
                .type(ClientType.COMPANY)
                .password("1234")
                .build();

        User u5 = User.builder()
                .email("Zimmer@gmail.com")
                .type(ClientType.COMPANY)
                .password("1234")
                .build();

        User u6 = User.builder()
                .email("Castro@gmail.com")
                .type(ClientType.COMPANY)
                .password("1234")
                .build();

        User u7 = User.builder()
                .email("MoonActive@gmail.com")
                .type(ClientType.COMPANY)
                .password("1234")
                .build();

        User u8 = User.builder()
                .email("McDonald@gmail.com")
                .type(ClientType.COMPANY)
                .password("1234")
                .build();

        User u9 = User.builder()
                .email("Ivory@gmail.com")
                .type(ClientType.COMPANY)
                .password("1234")
                .build();

        User u10 = User.builder()
                .email("Intel@gmail.com")
                .type(ClientType.COMPANY)
                .password("1234")
                .build();

        User u11 = User.builder()
                .email("Roni@gmail.com")
                .type(ClientType.CUSTOMER)
                .password("1234")
                .build();

        User u12 = User.builder()
                .email("Noam@gmail.com")
                .type(ClientType.CUSTOMER)
                .password("1234")
                .build();

        User u13 = User.builder()
                .email("Revital@gmail.com")
                .type(ClientType.CUSTOMER)
                .password("1234")
                .build();

        User u14 = User.builder()
                .email("Yamit@gmail.com")
                .type(ClientType.CUSTOMER)
                .password("1234")
                .build();

        User u15 = User.builder()
                .email("Grace@gmail.com")
                .type(ClientType.CUSTOMER)
                .password("1234")
                .build();

        User u16 = User.builder()
                .email("Aharon@gmail.com")
                .type(ClientType.CUSTOMER)
                .password("1234")
                .build();

        User u17 = User.builder()
                .email("Neomi@gmail.com")
                .type(ClientType.CUSTOMER)
                .password("1234")
                .build();

        User u18 = User.builder()
                .email("Adi@gmail.com")
                .type(ClientType.CUSTOMER)
                .password("1234")
                .build();

        User u19 = User.builder()
                .email("Rachel@gmail.com")
                .type(ClientType.CUSTOMER)
                .password("1234")
                .build();

        User u20 = User.builder()
                .email("Noga@gmail.com")
                .type(ClientType.CUSTOMER)
                .password("1234")
                .build();

        userRepository.saveAll(List.of(u0,u1,u2,u3,u4,u5,u6,u7,u8,u9,u10,u11,u12,u13,u14,u15,u16,u17,u18,u19,u20));

                Customer c1 = Customer.builder()
                .firstName("Roni")
                .lastName("Barak")
                .email(u11.getEmail())
                .password(u11.getPassword())
                .id((int) u11.getId()).build();
        Customer c2 = Customer.builder()
                .firstName("Noam")
                .lastName("Menachem")
                .email(u12.getEmail())
                .password(u12.getPassword())
                .id((int) u12.getId()).build();
        Customer c3 = Customer.builder()
                .firstName("Revital")
                .lastName("Shemi")
                .email(u13.getEmail())
                .password(u13.getPassword())
                .id((int) u13.getId()).build();
        Customer c4 = Customer.builder()
                .firstName("Yamit")
                .lastName("Herut")
                .email(u14.getEmail())
                .password(u14.getPassword())
                .id((int) u14.getId()).build();
        Customer c5 = Customer.builder()
                .firstName("Grace")
                .lastName("Jelen")
                .email(u15.getEmail())
                .password(u15.getPassword())
                .id((int) u15.getId()).build();
        Customer c6 = Customer.builder()
                .firstName("Aharon")
                .lastName("Lev")
                .email(u16.getEmail())
                .password(u16.getPassword())
                .id((int) u16.getId()).build();
        Customer c7 = Customer.builder()
                .firstName("Neomi")
                .lastName("Lilach")
                .email(u17.getEmail())
                .password(u17.getPassword())
                .id((int) u17.getId()).build();
        Customer c8 = Customer.builder()
                .firstName("Adi")
                .lastName("Shvat")
                .email(u18.getEmail())
                .password(u18.getPassword())
                .id((int) u18.getId()).build();
        Customer c9 = Customer.builder()
                .firstName("Rachel")
                .lastName("Hart")
                .email(u19.getEmail())
                .password(u19.getPassword())
                .id((int) u19.getId()).build();
        Customer c10 = Customer.builder()
                .firstName("Noga")
                .lastName("Barak")
                .email(u20.getEmail())
                .password(u20.getPassword())
                .id((int) u20.getId()).build();

        List<Customer> customers = List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);
        customerRepository.saveAll(customers);
        //customerRepository.findAll().forEach(System.out::println);

        //private static void loadCompanies() {
        //System.out.println(Art.COMPANIES);
        Company company1 = Company.builder()
                .name("Coca cola")
                .email(u1.getEmail())
                .password(u1.getPassword())
                .id((int) u1.getId()).build();
        Company company2 = Company.builder()
                .name("Shein")
                .email(u2.getEmail())
                .password(u2.getPassword())
                .id((int) u2.getId()).build();
        Company company3 = Company.builder()
                .name("MarketPlace")
                .email(u3.getEmail())
                .password(u3.getPassword())
                .id((int) u3.getId()).build();
        Company company4 = Company.builder()
                .name("Fox")
                .email(u4.getEmail())
                .password(u4.getPassword())
                .id((int) u4.getId()).build();
        Company company5 = Company.builder()
                .name("Zimmer")
                .email(u5.getEmail())
                .password(u5.getPassword())
                .id((int) u5.getId()).build();
        Company company6 = Company.builder()
                .name("Castro")
                .email(u6.getEmail())
                .password(u6.getPassword())
                .id((int) u6.getId()).build();
        Company company7 = Company.builder()
                .name("MoonActive")
                .email(u7.getEmail())
                .password(u7.getPassword())
                .id((int) u7.getId()).build();
        Company company8 = Company.builder()
                .name("McDonald")
                .email(u8.getEmail())
                .password(u8.getPassword())
                .id((int) u8.getId()).build();
        Company company9 = Company.builder()
                .name("Ivory")
                .email(u9.getEmail())
                .password(u9.getPassword())
                .id((int) u9.getId()).build();
        Company company10 = Company.builder()
                .name("Intel")
                .email(u10.getEmail())
                .password(u10.getPassword())
                .id((int) u10.getId()).build();

        List<Company> companies = List.of(company1, company2, company3, company4, company5, company6, company7, company8, company9, company10);
        companyRepository.saveAll(companies);
        //companyRepository.findAll().forEach(System.out::println);

        //private static void loadCoupons() {
        //System.out.println(Art.COUPONS);
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
        //couponRepository.findAll().forEach(System.out::println);

    }
}





