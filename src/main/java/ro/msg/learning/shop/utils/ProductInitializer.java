package ro.msg.learning.shop.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.entities.Supplier;
import ro.msg.learning.shop.repositories.ProductRepository;
import ro.msg.learning.shop.services.ProductService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class ProductInitializer {

    @Autowired
    private ProductService productService;

    @PostConstruct
    public void initProducts() {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("Telefoane");
        productCategory.setDescription("Aceasta categorie este dedicata telefoanelor");

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setName("Electrocasnice");
        productCategory2.setDescription("Aceasta categorie este dedicata electrocasnicelor");

        Supplier supplier = new Supplier();
        supplier.setName("Altex");

        Supplier supplier2 = new Supplier();
        supplier2.setName("Emag");

        Product product = new Product();
        product.setName("Samsung");
        product.setDescription("S9");
        product.setPrice(new BigDecimal(499));
        product.setWeight(0.200);
        product.setCategory(productCategory);
        product.setSupplier(supplier);

        Product product2 = new Product();
        product2.setName("Beko");
        product2.setDescription("Frigider");
        product2.setPrice(new BigDecimal(499));
        product2.setWeight(0.200);
        product2.setCategory(productCategory2);
        product2.setSupplier(supplier2);

        productService.saveProduct(product);
        productService.saveProduct(product2);

    }

}
