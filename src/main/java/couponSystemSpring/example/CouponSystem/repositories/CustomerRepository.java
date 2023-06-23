package couponSystemSpring.example.CouponSystem.repositories;

import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value = "SELECT id FROM `coupon-system`.customers WHERE email =?",nativeQuery = true)
    int getIdByEmail(String email);
    boolean existsByEmail(String email);
    @Query(value ="INSERT INTO `coupon-system`.`customers_vs_coupons` (`customer_id`, `coupon_id`) VALUES (?, ?)" ,nativeQuery = true)
    void addCouponForCustomer(Coupon coupon, int customerId);
    Customer findById(int customerId);
    // will return all the details about this customer
    List<Coupon> findCouponsById(int customerId);
    // should return all the customer coupons
}
