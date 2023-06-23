package couponSystemSpring.example.CouponSystem.beans;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {
    //TODO: maybe I should also add a field of customer? and write one to many
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
//    @ToString.Exclude
    private Company company;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToMany(mappedBy = "coupons")
    @ToString.Exclude
    List<Customer>customers;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;
//
//    public Coupon(int i, Company orElseThrow, Category movies, String s, String tta, Date valueOf, Date valueOf1, int i1, int i2, String http) {
//    }
//
//    public Coupon(int companyId, Optional<Company> singleCompany, Category category, String title, String description, Date startDate, Date endDate, int amount, double price, String image) {
//        this.company = getCompany();
//        this.category = category;
//        this.title = title;
//        this.description = description;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.amount = amount;
//        this.price = price;
//        this.image = image;
    }

