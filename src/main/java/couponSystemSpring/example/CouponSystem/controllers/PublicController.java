package couponSystemSpring.example.CouponSystem.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// only to check that everything is ok
@RestController
@RequestMapping("/coupons")
@CrossOrigin
public class PublicController {
    // I need to write a query that will return all the coupons
    @GetMapping
    public String getAllCoupons(){
        return "I need to think how to return all coupons here";
    }
}
