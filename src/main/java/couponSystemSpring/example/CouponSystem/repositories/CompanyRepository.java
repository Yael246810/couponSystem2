package couponSystemSpring.example.CouponSystem.repositories;

import couponSystemSpring.example.CouponSystem.beans.Company;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByName(String name);
    boolean existsByEmail(String email);
    @Query(value = "SELECT id FROM `coupon-system`.companies WHERE email =?",nativeQuery = true)
    int getIdByEmail(String email);
    Company findById(int companyId);
    //company details, and a single company from the data Base?
    List<Coupon> findCouponsById(int companyId);
    // get the company coupons
}
// TODO: to see if I need other functionalities that were existed in the generic Dao...