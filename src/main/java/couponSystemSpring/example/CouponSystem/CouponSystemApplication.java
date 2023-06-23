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
		System.out.println("yael");
		// TODO: I have a problem in CompanyServiceTest...
		//  somehow it doesn't add the coupon. there is also a problem with get company coupons...

		// TODO: to see the recording about the Tokens.. security, and after that to start doing tests.
		// TODO: to build the controllers
	}
}
