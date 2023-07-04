package couponSystemSpring.example.CouponSystem.repositories;

import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import jakarta.transaction.Transactional;
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
@Transactional
    @Modifying
//    @Query(value ="INSERT INTO couponsystem2.customers_coupons(customers_id,coupons_id) VALUES (?, ?)" ,nativeQuery = true)
      @Query(value ="INSERT INTO couponsystem2.customers_coupons(customers_id,coupons_id) VALUES (?,?); " ,nativeQuery = true)
    void addCouponForCustomer(int customerId, long couponId);
//    @Modifying
//    void saveCustomersCoupons(int customerId, int couponId);


    // will return all the details about this customer
    //@Query(value = "SELECT * FROM `couponsystem2`.customers_coupons JOIN `couponsystem2`.coupons ON `couponsystem2`.customers_coupons.coupons_id = coupons.id WHERE customers_id = ?", nativeQuery = true)
//    @Query(value = "SELECT `couponsystem2`.coupons.id,`couponsystem2`.coupons.company_id,`couponsystem2`.coupons.category,`couponsystem2`.coupons.title,`couponsystem2`.coupons.description,`couponsystem2`.coupons.start_date,`couponsystem2`.coupons.end_date,`couponsystem2`.coupons.amount,`couponsystem2`.coupons.price,`couponsystem2`.coupons.image FROM `couponsystem2`.customers_coupons JOIN `couponsystem2`.coupons ON `couponsystem2`.customers_coupons.coupons_id = `couponsystem2`.coupons.id WHERE `customers_id` =?;", nativeQuery = true)
    @Query(value = "SELECT *\n" +
            "FROM `couponsystem2`.coupons\n" +
            "WHERE id IN (\n" +
            "    SELECT `couponsystem2`.customers_coupons.coupons_id\n" +
            "    FROM `couponsystem2`.customers_coupons\n" +
            "    JOIN `couponsystem2`.coupons ON `couponsystem2`.customers_coupons.coupons_id = coupons.id\n" +
            "    WHERE customers_id = 1\n" +
            ");", nativeQuery = true)
    List<Coupon> findByCouponsId(int customerId);
    // should return all the customer coupons
}
