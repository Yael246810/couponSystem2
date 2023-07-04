package couponSystemSpring.example.CouponSystem.clr;

import couponSystemSpring.example.CouponSystem.beans.Company;
import couponSystemSpring.example.CouponSystem.beans.Customer;
import couponSystemSpring.example.CouponSystem.services.AdminService;
import couponSystemSpring.example.CouponSystem.services.ClientService;
import couponSystemSpring.example.CouponSystem.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Order(2)
public class AdminServiceTest implements CommandLineRunner {
    @Autowired
    AdminService adminService;

    @Override
    public void run(String... args) throws Exception {
        // Login
        TestUtils.test("Admin service - problem with the login - wrong email address");
        System.out.println(((ClientService) adminService).login("sivan@gmail.com", "admin"));
        TestUtils.test("Admin service - problem with the login - wrong password");
        System.out.println(((ClientService) adminService).login("admin@admin.com", "admin123"));
        TestUtils.test("Admin service - problem with the login - wrong email address and password");
        System.out.println(((ClientService) adminService).login("sivan@gmail.com", "admin123"));
        TestUtils.test("Admin service - the login succeeded");
        System.out.println(((ClientService) adminService).login("admin@admin.com", "admin"));
        System.out.println("-----------------------------------------------------------------------------");

        //Add company
        Company companyToAdd = null;
        TestUtils.test("Admin service - add company - id already exists");
        companyToAdd = adminService.getSingleCompany(1).orElseThrow(() -> new Exception("company not exists"));
        companyToAdd.setName("maor");
        companyToAdd.setEmail("maor@gmal.com");
        try {
            adminService.add(companyToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        TestUtils.test("Admin service -  - name already exists");
        companyToAdd = adminService.getSingleCompany(1).orElseThrow(() -> new Exception("company not exists"));
        companyToAdd.setId(0);
        companyToAdd.setEmail("maor@gmail.com");
        try {
            adminService.add(companyToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        TestUtils.test("Admin service -  - email address already exists");
        companyToAdd = adminService.getSingleCompany(1).orElseThrow(() -> new Exception("company not exists"));
        companyToAdd.setId(0);
        companyToAdd.setName("maor");
        try {
            adminService.add(companyToAdd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        TestUtils.test("Admin service - succeeded");

        companyToAdd = new Company("new company", "new.company@info.com", "1234");
        adminService.add(companyToAdd);
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("-----------------------------------------------------------------------------");

        // Update company
        TestUtils.test("Admin service - update company - cannot update id that doesn't exist");
        Company companyToUpdate = adminService.getSingleCompany(1).orElseThrow(() -> new Exception("company does not exist"));
        companyToUpdate.setId(9000);
        try {
            adminService.updateCompany(companyToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        TestUtils.test("Admin service - update company - cannot update company id");
        companyToUpdate = adminService.getSingleCompany(1).orElseThrow(() -> new Exception("company does not exist"));
        companyToUpdate.setId(2);
        try {
            adminService.updateCompany(companyToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        TestUtils.test("Admin service - update company - cannot update company name");
        companyToUpdate = adminService.getSingleCompany(1).orElseThrow(() -> new Exception("company does not exist"));
        companyToUpdate.setName("yael");
        try {
            adminService.updateCompany(companyToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        TestUtils.test("Admin service - succeeded to update company");
        companyToUpdate = adminService.getSingleCompany(9).orElseThrow(() -> new Exception("company does not exist"));
        companyToUpdate.setEmail("updated.mail@info.com");
        companyToUpdate.setPassword("1111");
        adminService.updateCompany(companyToUpdate);
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------");

        //Delete company method
        TestUtils.test("Admin service - delete company - cannot delete");
        Company companyToDelete = null;

        try {
            var companyOptional = adminService.getSingleCompany(13);
            companyToDelete = companyOptional.orElseThrow(() -> new Exception("company does not exist"));
            adminService.deleteCompany(companyToDelete.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        TestUtils.test("Admin service - delete company - succeeded");
        Company companyToDelete1 = null;
        var companyOptional = adminService.getSingleCompany(11);
        companyToDelete1 = companyOptional.orElseThrow(()->new Exception("cannot delete company"));
        adminService.deleteCompany(companyToDelete1.getId());
        System.out.println(adminService.getAllCompanies());
        Company companyWithCoupon = adminService.getSingleCompany(7).orElseThrow();
        adminService.deleteCompany(companyWithCoupon.getId());
        System.out.println("------------------------------------------------------------------------------");

        // get all companies method//
        TestUtils.test("Admin service - get all companies - succeeded");
        adminService.getAllCompanies().forEach(System.out::println);
        System.out.println("---------------------------------------------------------------");

        //Get a single company method
        TestUtils.test("Admin service - get single company - succeeded");
        Optional<Company> getSingle = adminService.getSingleCompany(4);
        System.out.println(getSingle);
        System.out.println("----------------------------------------------------------------");

        //add customer
        TestUtils.test("Admin service - add a customer");
        Customer customer = new Customer("Maor", "Schwartz", "maor@walla.com", "1234");
        adminService.addCustomer(customer);
        System.out.println(adminService.getAllCustomers());
        System.out.println("--------------------------------------------------------------------");

        //Update customer
        TestUtils.test("Admin service - update customer - id does not exist");
        Optional<Customer> optionalCustomer = adminService.getSingleCustomer(companyToUpdate.getId()).orElseThrow();
        Customer customerToUpdate = optionalCustomer.get();
        try {
            adminService.updateCustomer(9000, customerToUpdate);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        TestUtils.test("Admin service - succeeded to update customer");
        customerToUpdate = adminService.getSingleCustomer(1).orElseThrow().orElseThrow(() -> new Exception("customer does not exist"));
        customerToUpdate.setEmail("noga.mail@info.com");
        customerToUpdate.setPassword("1111");
        customerToUpdate.setFirstName("Noga");
        customerToUpdate.setLastName("Barak");

        adminService.updateCustomer(customerToUpdate.getId(), customerToUpdate);
        Customer updatedCustomer = adminService.getSingleCustomer(1).orElseThrow().orElseThrow();
        System.out.println(updatedCustomer);
        System.out.println("-------------------------------------------------------------------");

        //Delete customer
        TestUtils.test("Admin service - delete customer - cannot delete because customer id does not exist");
        Customer customerToDelete = null;

        try {
            Optional<Customer> customerOptional = adminService.getSingleCustomer(13).orElseThrow();
            customerToDelete = customerOptional.orElseThrow(() -> new Exception("customer does not exist"));
            adminService.deleteCustomer(customerToDelete.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        TestUtils.test("Admin service - delete customer - delete customer but coupon list is empty or null");
        try {
            Optional<Customer> customerOptional = adminService.getSingleCustomer(3).orElseThrow();
            customerToDelete = customerOptional.orElseThrow(() -> new Exception("customer does not exist"));
            adminService.deleteCustomer(customerToDelete.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        TestUtils.test("Admin service - delete customer - succeeded");
        customerToDelete = adminService.getSingleCustomer(1).orElseThrow(() -> new Exception("customer does not exist")).orElseThrow();;
        adminService.deleteCustomer(customerToDelete.getId());
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------------");

        //Get all customers
        TestUtils.test("Admin service - get all customers - succeeded");
        adminService.getAllCustomers().forEach(System.out::println);
        System.out.println("---------------------------------------------------------------");

        //Get single customer
        TestUtils.test("Admin service - get single customer - succeeded");
        Optional<Customer> getSingleCustomer = adminService.getSingleCustomer(2).orElseThrow();
        System.out.println(getSingleCustomer);
        System.out.println("----------------------------------------------------------------");
    }
}
