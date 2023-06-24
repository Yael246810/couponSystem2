package couponSystemSpring.example.CouponSystem.exceptions;

public class CouponSystemException extends Exception{
    public CouponSystemException(ErrorMessage message) {
        super(message.getMessage());
    }
}
