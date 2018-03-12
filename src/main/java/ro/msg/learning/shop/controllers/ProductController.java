package ro.msg.learning.shop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.entities.Supplier;
import ro.msg.learning.shop.repositories.ProductRepository;
import ro.msg.learning.shop.services.ProductCategoryService;
import ro.msg.learning.shop.services.ProductService;
import ro.msg.learning.shop.services.SupplierService;
import ro.msg.learning.shop.utils.ProductDtoMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public Product getPproduct(@PathVariable("id") int productId) {

        return productService.readProduct(productId);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDto> getPproducts() {

        return ProductDtoMapper.productListToProductDtoList(productService.readProducts());
    }

}
