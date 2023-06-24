package couponSystemSpring.example.CouponSystem.repositories;

import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "SELECT id FROM `couponsystem2`.customers WHERE email =?", nativeQuery = true)
    int getIdByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying// doesn't expect a result set
    @Query(value ="INSERT INTO couponsystem2.customers_coupons (customers_id, coupons_id) VALUES (?, ?);" ,nativeQuery = true)
    int addCouponForCustomer(int customerId, int couponId);
//    @Modifying
//    void saveCustomersCoupons(int customerId, int couponId);

    Customer findById(int customerId);

    // will return all the details about this customer
    @Query(value = "SELECT * FROM `couponsystem2`.customers_coupons JOIN `couponsystem2`.coupons ON `couponsystem2`.customers_coupons.coupons_id = coupons.id WHERE customers_id = ?", nativeQuery = true)
    List<Coupon> findByCouponsId(int customerId);
    // should return all the customer coupons
}
