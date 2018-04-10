package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, optional=false)
    @JoinColumn(name="location_id")
    private Location shippedFrom;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    @JsonIgnore
    private Customer customer;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<OrderDetail> orderDetails;

}
