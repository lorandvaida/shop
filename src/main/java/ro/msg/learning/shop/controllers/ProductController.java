package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.services.ProductService;
import ro.msg.learning.shop.utils.ProductDtoMapper;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable("id") int productId) {

        return productService.readProduct(productId);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDto> getProducts() {

        return ProductDtoMapper.productListToProductDtoList(productService.readProducts());
    }

}
