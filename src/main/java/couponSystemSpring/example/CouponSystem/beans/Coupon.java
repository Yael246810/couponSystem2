package couponSystemSpring.example.CouponSystem.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @ToString.Exclude
    @JsonIgnore //TODO: originally it was only here
    @JoinColumn(name = "company_id")
    private Company company;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToMany(mappedBy = "coupons",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    List<Customer>customers;
    @Column(length = 50,nullable = false)
    private String title;
    @Column(length = 100,nullable = false)
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

