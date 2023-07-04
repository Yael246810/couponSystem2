package couponSystemSpring.example.CouponSystem.exceptions;

import lombok.Getter;
//TODO: to create only one companyIdNotFound, and not so many.
@Getter
public enum ErrorMessage {
    ADD_COMPANY_ID_ALREADY_EXISTS("cannot add company because the id already exists"),
    ADD_COMPANY_NAME_ALREADY_EXISTS("cannot add company because the company's name already exists"),
    ADD_COMPANY_EMAIL_ALREADY_EXISTS("cannot add company because the company's email already exists "),
    UPDATE_COMPANY_ID_NOT_EXISTS("cannot update company because company id doesn't exist"),
    UPDATE_COMPANY_CANNOT_UPDATE_ID("cannot update company because company's id is different"),
    UPDATE_COMPANY_CANNOT_UPDATE_NAME("cannot update company because company's name is different"),
    CANNOT_FIND_COMPANY_ID("cannot find company because company Id does not exist in the system"),
    PROBLEM_WITH_THE_LOGIN("invalid email/password of the user"),
    CANNOT_DELETE_COMPANY_ID_NOT_EXISTS("cannot delete company because company ID doesn't exist"),
    CANNOT_DELETE_COMPANY_COUPONS("cannot delete company coupons because company ID doesn't exist"),
    CANNOT_DELETE_COMPANY_COUPONS_THERE_ARE_NO_COUPONS("cannot delete company coupons because the coupon list is empty"),
    RESULTS_IS_EMPTY("results list is empty"),
    CANNOT_FIND_CUSTOMER_ID("cannot find customer find customer Id because customer Id does not exist in the system"),
    CUSTOMER_DOESNT_HAVE_COUPONS("customer doesn't have coupons"),
    COMPANY_ID_DOES_NOT_EXIST("cannot get company coupons because company Id does not exist"),
    COMPANY_ID_DOES_NOT_EXISTS("company id doesn't exists, cannot add company"),
    COMPANY_ID_DOES_NOT_EXIST_HERE("company id doesn't exists, cannot get company coupons by category"),
    CANNOT_GET_COMPANY_COUPONS_BY_PRICE("company id doesn't exists, cannot get company coupons by max price"),
    COMPANY_DID_NOT_LOGIN("company didn't login, cannot find company coupons"),
    CUSTOMER_ID_ALREADY_EXISTS("cannot add customer because customer id already exists"),
    CUSTOMER_EMAIL_ALREADY_EXISTS("cannot add customer because customer email already exists"),
    CANNOT_UPDATE_CUSTOMER_ID("cannot update customer Id"),
    CANNOT_DELETE_CUSTOMER_COUPONS("cannot delete customer coupons because customer ID doesn't exist"),
    CANNOT_DELETE_CUSTOMER_COUPONS_THERE_ARE_NO_COUPONS("cannot delete customer coupons because the coupon list is empty"),
    COUPON_TITLE_ALREADY_EXISTS("coupon title already exists in this company, we cannot add this coupon"),
    CANNOT_UPDATE_COUPON_CANNOT_UPDATE_COMPANY_ID("cannot update coupon because cannot update companyID"),
    UPDATE_COUPON_ID_NOT_EXISTS("id does not exists so we cannot update coupon"),
    UPDATE_COUPON_CANNOT_UPDATE_ID("cannot update coupon id, cannot change the given coupon id"),
    CUSTOMER_DOES_NOT_HAVE_THIS_COUPON("customer doesn't have this coupon so we cannot delete it"),
    CANNOT_DELETE_COUPON_ID_DOESNT_EXIST("cannot delete coupon because id doesn't exist"),
    CANNOT_GET_COMPANY_DETAILS_INVALID_ID("cannot get company details because company id doesn't exists"),
    CANNOT_GET_COMPANY_DETAILS_NOT_LOGGED_IN("cannot get company details because company didn't logged in"),
    CUSTOMER_ALREADY_HAS_THIS_COUPON("cannot purchase this coupon, because the customer already purchased it"),
    CANNOT_PURCHASE_COUPON("cannot purchase this coupon because it does not exist (amount = 0)"),
    CANNOT_PURCHASE_COUPON_WITH_EXPIRED_DATE("cannot purchase a coupon that expired"),
    CANNOT_GET_CUSTOMER_COUPONS("cannot get customer's coupons because he didn't logged in"),
    CANNOT_GET_CUSTOMER_COUPONS_BY_CATEGORY("cannot get customer coupons by category because customer didn't logged in"),
    CUSTOMER_DETAILS_ARE_NOT_AVAILABLE("customer details are not available because customer didn't logged in"),
    CANNOT_ADD_COUPON_ALREADY_EXISTS("cannot add coupon because the coupon already exists in the system"),
    SECURITY_EXCEPTION_CANNOT_CREATE_ADMIN_USER("cannot enter as admin"),
    SECURITY_EXCEPTION_USER_EMAIL("cannot enter the system. user email already exists"),
    SECURITY_EXCEPTION_USER_DOES_NOT_EXIST("your password or email is incorrect"),
    SECURITY_EXCEPTION_USER_NOT_ALLOWED("user is not an admin and cannot enter as an admin");

    private String message;
    ErrorMessage(String message) {
        this.message = message;
    }
}
