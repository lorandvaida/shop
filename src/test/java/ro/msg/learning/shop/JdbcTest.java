package ro.msg.learning.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.repositories.ProductRepo;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTest {


    @Autowired
    private ProductRepo productRepo;

    @Test
    public void createProduct() {

        Product product = new Product("Celular","Camera VGA, Calitate buna.", new BigDecimal(499), 0.200, 1, 1);

        Product savedProduct = productRepo.createProduct(product);
        Product newProduct = productRepo.findProductById(savedProduct.getId());

        assertNotNull(newProduct);
        assertEquals("Celular", newProduct.getName());
    }

}
