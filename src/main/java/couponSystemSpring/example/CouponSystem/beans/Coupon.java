package couponSystemSpring.example.CouponSystem.beans;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
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
    @ToString.Exclude
    private Company company;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToMany(mappedBy = "coupons",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    List<Customer>customers;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private double price;
    private String image;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Coupon other = (Coupon) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    }

