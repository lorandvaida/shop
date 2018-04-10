package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.repositories.ProductCategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public ProductCategory readOrder(int productCategoryId) {

        return productCategoryRepository.findOne(productCategoryId);
    }

    public List<ProductCategory> readOrders() {

        Iterable<ProductCategory> iterableProductCategories = productCategoryRepository.findAll();
        List<ProductCategory> productCategoryList = new ArrayList<>();

        if(iterableProductCategories != null) {
            for(ProductCategory productCategory : iterableProductCategories) {
                productCategoryList.add(productCategory);
            }
        }

        return productCategoryList;
    }

    public ProductCategory saveProductCategory(ProductCategory productCategory) {

        return productCategoryRepository.save(productCategory);
    }

    public void deleteProductCategory(int productCategoryId) {

        productCategoryRepository.delete(productCategoryId);
    }

    public void deleteAllProductCategories() {

        productCategoryRepository.deleteAll();
    }
}
