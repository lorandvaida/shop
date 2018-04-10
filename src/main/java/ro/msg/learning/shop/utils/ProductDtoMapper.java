package ro.msg.learning.shop.utils;

import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDtoMapper {

    public static ProductDto productToProductDto(Product product) {

        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setWeight(product.getWeight());
        productDto.setCategoryName(product.getCategory().getName());
        productDto.setSupplierName(product.getSupplier().getName());

        return productDto;
    }

    public static List<ProductDto> productListToProductDtoList(List<Product> productList) {

        List<ProductDto> productDtoList = new ArrayList<>();

        for(Product product : productList) {

            productDtoList.add(productToProductDto(product));
        }

        return productDtoList;
    }
}
