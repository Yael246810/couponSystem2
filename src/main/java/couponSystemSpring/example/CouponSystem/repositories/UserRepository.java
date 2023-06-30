package couponSystemSpring.example.CouponSystem.repositories;

import couponSystemSpring.example.CouponSystem.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email,String password);
}
