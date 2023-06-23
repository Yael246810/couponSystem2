package couponSystemSpring.example.CouponSystem.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrDetails {
    private final String title = "COUPON_SYSTEM_APP";
    private String description;
}
