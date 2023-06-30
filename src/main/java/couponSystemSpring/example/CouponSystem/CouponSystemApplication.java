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

		// TODO: to check it in postman and see that all the web part works
		//TODO: to see that the thread works.. (to put one or 2 coupons with expired date)

		//TODO: שאלות:
		// TODO: How do I know what to write in @GetMapping? by Delete post and so on.
		//TODO: Do I need to put the tokens in Login method? in All the methods.
		//TODO: when do I use register method? I don't really need this method because I generate everything in Init.
		//TODO: I need to throw an exception if the token is expired, and then the customer needs to do Login again.
	}
}
