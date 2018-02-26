package ro.msg.learning.shop.utils;

import org.springframework.jdbc.core.RowMapper;
import ro.msg.learning.shop.entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int i) throws SQLException {

        Product product = new Product();

        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setWeight(rs.getDouble("weight"));
        product.setCategory(rs.getInt("category"));
        product.setSuplier(rs.getInt("suplier"));

        return product;
    }
}
