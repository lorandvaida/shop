package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private double weight;
    private String categoryName;
    private String supplierName;



}
