package couponSystemSpring.example.CouponSystem.repositories;

import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.Company;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

//TODO: to create a query that delete all the expired coupons and then add it to the thread
@Repository
public interface CouponRepository extends JpaRepository<Coupon,Integer> {
    //TODO: do I need a query that delete a coupon from a customer?
//    @Query(value = "DELETE FROM `coupon-system`.customers_vs_coupons WHERE customer_id =?",nativeQuery = true)
//    void deleteCouponPurchase(int customerID, int couponID);
    //TODO: maybe I don't need this because if I delete the coupon then it should be deleted from customer_vs_coupons
    boolean existsByTitleAndCompany(String title,Company company);
    List<Coupon> findByIdAndPriceLessThan(double maxPrice, int customerId);
    List<Coupon> findByCategoryAndId(Category category, int customerId);
    List<Coupon> findByCompany_IdAndCategory(Category category, int companyId);
    List<Coupon> findByCompany_IdAndPriceLessThan(double maxPrice, int companyId);
    void deleteByEndDateBefore(LocalDate endDate);
    //TODO: to see which one of them is working
}
