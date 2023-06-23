package couponSystemSpring.example.CouponSystem.advice;

import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponSystemControllerAdvice {
    @ExceptionHandler(value = {CouponSystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDetails handleExceptions(Exception e){
        return new ErrDetails(e.getMessage());
    }
}
