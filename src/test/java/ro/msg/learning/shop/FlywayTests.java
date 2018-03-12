package ro.msg.learning.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlywayTests {
/*
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testReadAllProducts() {
        assertThat(productRepository.count()).isEqualTo(3);
    }

    @Test
    public void readOneProduct() {
        assertThat(productRepository.findOne(1)).isEqualTo(1);
    }

    @Test
    public void createOneProduct() {
        Product product = new Product(0,"Celular","Camera VGA, Calitate buna.", new BigDecimal(499), 0.200, 1, 1);
        productRepository.save(product);
        assertThat(productRepository.count()).isEqualTo(4);
    }

    @Test
    public void updateOneProduct() {
        Product product = productRepository.findOne(4);
        assertThat(product.getName()).equals("Celular");
    }

    @Test
    public void deleteOneProduct() {
        productRepository.delete(1);
        assertThat(productRepository.count()).isEqualTo(3);
    }
*/
}
