package couponSystemSpring.example.CouponSystem.services;

import couponSystemSpring.example.CouponSystem.repositories.CompanyRepository;
import couponSystemSpring.example.CouponSystem.repositories.CouponRepository;
import couponSystemSpring.example.CouponSystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientService {
    @Autowired
    protected CouponRepository couponRepository;

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected CompanyRepository companyRepository;

    public abstract boolean login(String email,String password);
}

