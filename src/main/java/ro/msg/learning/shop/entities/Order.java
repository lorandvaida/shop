package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, optional=false)
    @JoinColumn(name="shipped_from")
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
