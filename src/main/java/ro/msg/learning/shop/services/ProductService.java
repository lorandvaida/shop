package ro.msg.learning.shop.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product readProduct(int productId) {

        return productRepository.findOne(productId);
    }

    public List<Product> readProducts() {

        Iterable<Product> iterableProducts = productRepository.findAll();
        List<Product> productList = new ArrayList<>();

        if(iterableProducts != null) {
            for(Product product : iterableProducts) {
                productList.add(product);
            }
        }

        return productList;
    }

    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    public  void deleteProduct(int productId) {

        productRepository.delete(productId);
    }

    public void deleteAllProducts() {

        productRepository.deleteAll();
    }

}
