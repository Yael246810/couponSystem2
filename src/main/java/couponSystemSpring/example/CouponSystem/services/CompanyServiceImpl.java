package couponSystemSpring.example.CouponSystem.services;

import couponSystemSpring.example.CouponSystem.beans.Category;
import couponSystemSpring.example.CouponSystem.beans.Company;
import couponSystemSpring.example.CouponSystem.beans.Coupon;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;
import couponSystemSpring.example.CouponSystem.exceptions.ErrorMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {
    Set<String> loggedInCompanies = new HashSet<>();

    @Override
    public boolean login(String email, String password) {
        boolean login = false;

        if (companyRepository.existsByEmail(email)) {
            int companyId = companyRepository.getIdByEmail(email);
            String companyPassword = companyRepository.findById(companyId).getPassword();
            String companyEmail = companyRepository.findById(companyId).getEmail();

            if (companyPassword.equals(password) & companyEmail.equals(email)) {
                loggedInCompanies.add(email);
                login = true;
            }
        }
        return login;
    }

    @Override
    public int getIdFromDB(String email) {
        return this.companyRepository.getIdByEmail(email);
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemException {
        long couponId = coupon.getId();
        int couponCompanyId = coupon.getCompany().getId();
        Company company = coupon.getCompany();
        String title = coupon.getTitle();
        //TODO: to check if the logic is reasonable here
        if (!couponRepository.existsById((int) couponId)) {
            boolean companyContainsCouponWithThisTitle = couponRepository.existsByTitleAndCompany( title,coupon.getCompany());

            if (companyContainsCouponWithThisTitle) {
                throw new CouponSystemException(ErrorMessage.COUPON_TITLE_ALREADY_EXISTS);
            }
            couponRepository.save(coupon);
            company.getCoupons().add(coupon);
        }
        if (couponRepository.existsById((int) coupon.getId())) {
            throw new CouponSystemException(ErrorMessage.CANNOT_ADD_COUPON_ALREADY_EXISTS);
        }
    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException {
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrorMessage.UPDATE_COUPON_ID_NOT_EXISTS);
        }
        if (couponId != coupon.getId()) {
            throw new CouponSystemException(ErrorMessage.UPDATE_COUPON_CANNOT_UPDATE_ID);
        }
        //TODO: to check that this if statement is ok
        if (coupon.getCompany().getId() != couponRepository.findById(couponId).get().getCompany().getId()) {
            throw new CouponSystemException(ErrorMessage.CANNOT_UPDATE_COUPON_CANNOT_UPDATE_COMPANY_ID);
        }
        this.couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(Coupon coupon) throws CouponSystemException {
        int couponId = (int) coupon.getId();
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrorMessage.CANNOT_DELETE_COUPON_ID_DOESNT_EXIST);
        }
        couponRepository.delete(coupon);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId) throws CouponSystemException {
        Company company = companyRepository.findById(companyId);
        String companyEmail = company.getEmail();

        if (!loggedInCompanies.contains(companyEmail)) {
            throw new CouponSystemException(ErrorMessage.COMPANY_DID_NOT_LOGIN);
        }
        return companyRepository.findByCouponsCompanyId(companyId);
    }

    @Override
    public List<Coupon> getCompanyCouponsByCategory(Category category, int companyId) throws CouponSystemException {
        String companyEmail = companyRepository.findById(companyId).getEmail();

        if (!loggedInCompanies.contains(companyEmail)) {
            throw new CouponSystemException(ErrorMessage.COMPANY_DID_NOT_LOGIN);
        }
        List<Coupon> coupons = getCompanyCoupons(companyId);

        if (coupons == null || coupons.isEmpty()) {
            return new ArrayList<>();
        }
        return couponRepository.findByCompany_IdAndCategory(category, companyId);
    }


    @Override
    public List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice, int companyId) throws CouponSystemException {
        Company company = companyRepository.findById(companyId);
        String email = company.getEmail();
        if (!loggedInCompanies.contains(email)) {
            throw new CouponSystemException(ErrorMessage.CANNOT_GET_COMPANY_COUPONS_BY_PRICE);
        }
        return couponRepository.findByCompany_IdAndPriceLessThan(maxPrice, companyId);
    }

    @Override
    public Company getCompanyDetails(int companyId) throws CouponSystemException {
        Company company = companyRepository.findById(companyId);
        String email = company.getEmail();
        if (!loggedInCompanies.contains(email)) {
            throw new CouponSystemException(ErrorMessage.CANNOT_GET_COMPANY_DETAILS_INVALID_ID);
        }
        return companyRepository.findById(companyId);
    }
}
