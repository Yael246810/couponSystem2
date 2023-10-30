package couponSystemSpring.example.CouponSystem.repositories;

import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.Company;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    boolean existsByTitleAndCompany(String title,Company company);
    @Query(value = "select * from coupons inner join customers_coupons on coupons_id= id where customers_id=? and price<?",nativeQuery = true)
    List<Coupon> findByCustomerIdAndMaxPrice(int customerId,double maxPrice);

//    @Query(value = "select * from coupons inner join customers_coupons on coupons_id= id where customers_id=? and coupons.category=?",nativeQuery = true)
//    List<Coupon> findByCategoryAndId(Category category, int customerId);
    List<Coupon> findByCategoryAndId(Category category, int customerId);
    List<Coupon> findByCompany_IdAndCategory(Category category, int companyId);
    List<Coupon> findByCompany_IdAndPriceLessThan(double maxPrice, int companyId);
    void deleteByEndDateBefore(LocalDate endDate);
    @Query(value = "SELECT * FROM couponsystem2.coupons where coupons.company_id=?",nativeQuery = true)
    List<Coupon> findByCompany_Id(int companyId);
}
