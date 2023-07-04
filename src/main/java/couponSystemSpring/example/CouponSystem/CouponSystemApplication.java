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
	}
}
