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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String firstName;

    private String lastName;

    private String username;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonIgnore
    private List<Order> orders;

}
