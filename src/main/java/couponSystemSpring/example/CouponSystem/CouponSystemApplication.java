package couponSystemSpring.example.CouponSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class CouponSystemApplication {
	public static void main(String[] args) {
		System.out.println("Start");
		ApplicationContext ctx = SpringApplication.run(CouponSystemApplication.class, args);
		System.out.println("End");

		//TODO: problems in Admin - get all companies, get all customers (in Postman) and get customer coupons by price gives an empty list
		//TODO: the customers cannot do Login.... and the methods suddenly don't work - the problem is with the method: LoginManager. and to make sure that I use: IsUserAllowed.
	}
}
