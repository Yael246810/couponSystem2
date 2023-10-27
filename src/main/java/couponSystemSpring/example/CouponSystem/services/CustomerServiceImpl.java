package couponSystemSpring.example.CouponSystem.services;

import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.exceptions.ErrorMessage;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService{
    Set<String> loggedInCustomers = new HashSet<>();
    @Override
    public boolean login(String email, String password) {
        boolean login = false;
        if (customerRepository.existsByEmail(email)) {
            int customerId = customerRepository.getIdByEmail(email);
            String customerEmail = customerRepository.findById(customerId).orElseThrow().getEmail();
            String customerPassword = customerRepository.findById(customerId).orElseThrow().getPassword();

            if (customerEmail.equals(email) && customerPassword.equals(password)) {
                loggedInCustomers.add(customerEmail);
                login = true;
            }
        }
        return login;
    }
    @Override
    public Coupon purchaseCoupon(Long couponId, int customerId) throws Exception {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new CouponSystemException(ErrorMessage.CANNOT_FIND_CUSTOMER_ID));
        // TODO: 28/06/2023 NOT LIKE A BOSS
        Customer customer = customerRepository.findById(customerId).orElseThrow((()->new Exception("customer does not exist")));
        List<Coupon> customerCoupons = customer.getCoupons();

       // List<Coupon> customerCoupons = customerRepository.findByCouponsId(customerId);
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
        if (!couponRepository.existsById((long) couponId)){
            couponRepository.save(coupon);
        }
        customerRepository.addCouponForCustomer(customerId,couponId);
        return coupon;
    }
    @Override
    public void deleteCouponPurchased(Long couponId, int customerId) throws CouponSystemException {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        Coupon coupon = couponRepository.findById(couponId).orElseThrow();
        List<Coupon> coupons = customer.getCoupons();
        boolean isCouponExists = coupons.contains(coupon);

        if (!isCouponExists){
            throw new CouponSystemException(ErrorMessage.CUSTOMER_DOES_NOT_HAVE_THIS_COUPON);
        }
        coupons.remove(coupon);
        customerRepository.save(customer);
    }
    @Override
    public List<Coupon> getCustomerCoupons(int customerId) throws Exception {
        Optional<Customer> customer = customerRepository.findById(customerId);
        String email = customer.orElseThrow().getEmail();

        if (!loggedInCustomers.contains(email)) {
            throw new CouponSystemException(ErrorMessage.CANNOT_GET_CUSTOMER_COUPONS);
        }

        if (customer.orElseThrow().getCoupons() != null && !customer.orElseThrow().getCoupons().isEmpty()) {
            // TODO: 28/06/2023 NOT WORKS LIKE A BOSS
            Customer customer2 = customerRepository.findById(customerId).orElseThrow((()->new Exception("FOYS")));
            List<Coupon> customerCoupons = customer2.getCoupons();
            return  customerCoupons;
        }
        return new ArrayList<>();
    }

    @Override
    public List<Coupon> getCustomerCouponsByCategory(Category category, int customerId) throws Exception {
        // TODO: 28/06/2023 NOT LIKE A BOSS
        String customerEmail = customerRepository.findById(customerId).orElseThrow().getEmail();
        Optional<Customer> customer = customerRepository.findById(customerId);

        if (!loggedInCustomers.contains(customerEmail)) {
            throw new CouponSystemException(ErrorMessage.CANNOT_GET_CUSTOMER_COUPONS_BY_CATEGORY);
        }
        if (getCustomerCoupons(customerId) != null && !getCustomerCoupons(customerId).isEmpty()) {
//            return couponRepository.findByCategoryAndId(category,customerId);
            return customer.get().getCoupons()
                    .stream()
                    .filter(coupon -> coupon.getCategory() == category)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<Coupon> getCustomerCouponsUntilPrice(int customerId,double max) throws CouponSystemException {
        String customerEmail = customerRepository.findById(customerId).orElseThrow().getEmail();

        if (loggedInCustomers.contains(customerEmail)) {
            return couponRepository.findByCustomerIdAndMaxPrice(customerId,max);
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<Customer> getCustomerDetails(int customerId) throws CouponSystemException {
        Optional<Customer> customer = customerRepository.findById(customerId);
        String customerEmail = customer.orElseThrow(() -> new CouponSystemException(ErrorMessage.CANNOT_FIND_CUSTOMER_ID)).getEmail();
        if (!loggedInCustomers.contains(customerEmail)) {
            throw new CouponSystemException(ErrorMessage.CUSTOMER_DETAILS_ARE_NOT_AVAILABLE);
        }
        return customer;
    }
}
