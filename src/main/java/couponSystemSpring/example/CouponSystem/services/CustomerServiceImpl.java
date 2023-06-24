package couponSystemSpring.example.CouponSystem.services;

import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.exceptions.ErrorMessage;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService{
    Set<String> loggedInCustomers = new HashSet<>();
    @Override
    public boolean login(String email, String password) {
        boolean login = false;
        if (customerRepository.existsByEmail(email)) {
            int customerId = customerRepository.getIdByEmail(email);
            String customerEmail = customerRepository.findById(customerId).getEmail();
            String customerPassword = customerRepository.findById(customerId).getPassword();

            if (customerEmail.equals(email) && customerPassword.equals(password)) {
                if (loggedInCustomers.add(customerEmail)) {
                    login = true;
                }
            }
        }
        return login;
    }

    @Override
    public int getIdFromDB(String email) {
        return this.customerRepository.getIdByEmail(email);
    }
    @Override
    public void purchaseCoupon(Coupon coupon, int customerId) throws CouponSystemException {
        List<Coupon> customerCoupons = customerRepository.findByCouponsId(customerId);
        int couponId = (int) coupon.getId();
        if (customerCoupons != null && !customerCoupons.isEmpty()) {
            //TODO: maybe I can don't need what we did with the var?
            for (var a : customerCoupons) {
                if(a.getId() == coupon.getId()){
                    throw new CouponSystemException(ErrorMessage.CUSTOMER_ALREADY_HAS_THIS_COUPON);
                }
            }
        }
        if (coupon.getAmount() == 0) {
            throw new CouponSystemException(ErrorMessage.CANNOT_PURCHASE_COUPON);
        }
        Date now = Date.valueOf(LocalDate.now());
        if(coupon.getEndDate().before(now)) {
            throw new CouponSystemException(ErrorMessage.CANNOT_PURCHASE_COUPON_WITH_EXPIRED_DATE);
        }
        customerRepository.addCouponForCustomer(customerId,couponId);
    }
//TODO: I need to check if it deletes also the coupon from the customer_vs_coupons....
    @Override
    public void deleteCouponPurchased(Coupon coupon, int customerId) throws CouponSystemException {
        if (!customerRepository.findById(customerId).getCoupons().contains(coupon)){
            throw new CouponSystemException(ErrorMessage.CUSTOMER_DOES_NOT_HAVE_THIS_COUPON);
        }
        customerRepository.findById(customerId).getCoupons().remove(coupon);
        couponRepository.delete(coupon);
    }
    @Override
    public List<Coupon> getCustomerCoupons(int customerId) throws CouponSystemException {
        Customer customer = customerRepository.findById(customerId);
        String email = customer.getEmail();

        if (!loggedInCustomers.contains(email)) {
            throw new CouponSystemException(ErrorMessage.CANNOT_GET_CUSTOMER_COUPONS);
        }
        if (customer.getCoupons() != null && !customer.getCoupons().isEmpty()) {
            return customerRepository.findByCouponsId(customerId);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Coupon> getCustomerCouponsByCategory(Category category, int customerId) throws CouponSystemException {
        String customerEmail = customerRepository.findById(customerId).getEmail();

        if (!loggedInCustomers.contains(customerEmail)) {
            throw new CouponSystemException(ErrorMessage.CANNOT_GET_CUSTOMER_COUPONS_BY_CATEGORY);
        }
        if (getCustomerCoupons(customerId) != null && !getCustomerCoupons(customerId).isEmpty()) {
            return couponRepository.findByCategoryAndId(category,customerId);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Coupon> getCustomerCouponsUntilMaxPrice(double maxPrice, int customerId) throws CouponSystemException {
        String customerEmail = customerRepository.findById(customerId).getEmail();

        if (loggedInCustomers.contains(customerEmail)) {
            couponRepository.findByIdAndPriceLessThan(maxPrice,customerId);
        }
        return new ArrayList<>();
    }

    @Override
    public Customer getCustomerDetails(int customerId) throws CouponSystemException {
        String customerEmail = customerRepository.findById(customerId).getEmail();

        if (!loggedInCustomers.contains(customerEmail)) {
            throw new CouponSystemException(ErrorMessage.CUSTOMER_DETAILS_ARE_NOT_AVAILABLE);
        }
        return customerRepository.findById(customerId);
    }
}
