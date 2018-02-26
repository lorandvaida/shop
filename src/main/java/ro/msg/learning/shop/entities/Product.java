package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Getter
@Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private double weight;
    private int category;
    private int suplier;

    public Product() {}

    public Product(String name, String description, BigDecimal price, double weight, int category, int suplier) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.category = category;
        this.suplier = suplier;
    }
}