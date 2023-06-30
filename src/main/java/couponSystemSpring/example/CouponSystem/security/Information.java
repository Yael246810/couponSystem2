package couponSystemSpring.example.CouponSystem.security;

import couponSystemSpring.example.CouponSystem.beans.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Information {
    private int id;
    private LocalDateTime time;
    private ClientType clientType;

}
