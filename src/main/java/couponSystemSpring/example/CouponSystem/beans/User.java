package couponSystemSpring.example.CouponSystem.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    @Id
    private long id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ClientType type;
}
