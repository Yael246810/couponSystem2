package couponSystemSpring.example.CouponSystem.repositories;

import couponSystemSpring.example.CouponSystem.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email,String password);
    @Query(value = "SELECT id FROM `couponsystem2`.users WHERE email =?",nativeQuery = true)
    long getIdByEmail(String email);
//    @Query(value = "SELECT email FROM `couponsystem2`.users WHERE id =?",nativeQuery = true)
//    User findByUserId(long userId);
}
