package ro.msg.learning.shop.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product readProduct(int productId) {

        return productRepository.findOne(productId);
    }

    public List<Product> readProducts() {

        return productRepository.findAll();
    }
}
