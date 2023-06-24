package couponSystemSpring.example.CouponSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//for the thread
public class CouponSystemApplication {

	public static void main(String[] args) {
		System.out.println("Start");
		ApplicationContext ctx = SpringApplication.run(CouponSystemApplication.class, args);
		System.out.println("End");

		//TODO: problems in companyTest and customerTest
		//TODO: I don't have a table of company_vs_coupons in the database...
		// TODO: to see the recording about the Tokens...
		// TODO: to build the controllers
	}
}
