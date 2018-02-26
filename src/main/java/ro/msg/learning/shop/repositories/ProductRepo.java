package ro.msg.learning.shop.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.utils.ProductRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class ProductRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //@Transactional(readOnly=true)
    public List<Product> findAllProduct() {

        return jdbcTemplate.query("select * from product", new ProductRowMapper());
    }

    //@Transactional(readOnly=true)
    public Product findProductById(int id) {

        return jdbcTemplate.queryForObject("select * ftom product where id=?",new Object[]{id}, new ProductRowMapper());
    }

    @Transactional(readOnly=true)
    public Product createProduct(Product product) {

        final String sql = "insert into product(name,description,price,weight,category,suplier) values (?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, product.getName());
                ps.setString(2, product.getDescription());
                ps.setBigDecimal(3, product.getPrice());
                ps.setDouble(4, product.getWeight());
                ps.setInt(5, product.getCategory());
                ps.setInt(6, product.getSuplier());
                return ps;
            }
        }, keyHolder);

        int newProductId = keyHolder.getKey().intValue();
        product.setId(newProductId);


        return product;
    }

}
