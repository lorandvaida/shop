package ro.msg.learning.shop.utils;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.repositories.ProductRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class ProductInitializer {


    private ProductRepository productRepository;

    public ProductInitializer(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @PostConstruct
    public void initProducts() {

        Product p0 = new Product("Celular","Camera VGA, Calitate buna.", new BigDecimal(499), 0.200, 1, 1);
        Product p1 = new Product("Covor","Covor persan adus din Ambipur.", new BigDecimal(1000), 3, 1, 1);
        Product p2 = new Product("Cofraj oua","Izolare fonica garantata.", new BigDecimal(1), 0.01, 1, 1);
        Product p3 = new Product("Coada de matura","Lungime cuprinsa intre 150 si 180 de cm.", new BigDecimal(29), 0.100, 1, 1);
        Product p4 = new Product("Televizor Sport Diamant","Televizor alb-negru, prinde TVR 1 fara probleme.", new BigDecimal(1599), 93, 1, 1);

        productRepository.save(Arrays.asList(p0, p1, p2, p3, p4));
    }

}
