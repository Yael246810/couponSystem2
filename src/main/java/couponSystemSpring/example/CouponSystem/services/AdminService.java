package couponSystemSpring.example.CouponSystem.services;

import couponSystemSpring.example.CouponSystem.beans.Company;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import couponSystemSpring.example.CouponSystem.exceptions.CouponSystemException;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    void add(Company company) throws Exception;
    void updateCompany(Company company) throws CouponSystemException;
    void deleteCompany(int companyId) throws CouponSystemException;
    List<Company> getAllCompanies();
    Optional<Company> getSingleCompany(int companyId) throws CouponSystemException;
    void addCustomer(Customer customer) throws CouponSystemException;
    void updateCustomer(int customerId,Customer customer) throws CouponSystemException;
    void deleteCustomer(int customerId) throws CouponSystemException;
    List<Customer>getAllCustomers();
    Optional<Optional<Customer>> getSingleCustomer(int customerId) throws CouponSystemException;
}
