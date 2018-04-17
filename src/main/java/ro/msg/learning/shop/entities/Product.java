package ro.msg.learning.shop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;

    private Double price;

    private double weight;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_category")
    @JsonIgnore
    private ProductCategory category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier")
    @JsonIgnore
    private Supplier supplier;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonIgnore
    private List<Stock> stocks;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderDetail> orderDetails;

}