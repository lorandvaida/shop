package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    @JsonIgnore
    private List<Stock> stocks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shippedFrom")
    @JsonIgnore
    private List<Order> orders;

}