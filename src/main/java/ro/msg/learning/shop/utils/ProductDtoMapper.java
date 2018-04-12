package ro.msg.learning.shop.utils;

import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entities.Product;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductDtoMapper {

    public static List<ProductDto> productListToProductDtoList(List<Product> productList) {

        Function<Product, ProductDto> productToProductDto
                = product -> {

            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setWeight(product.getWeight());
            productDto.setCategoryName(product.getCategory().getName());
            productDto.setSupplierName(product.getSupplier().getName());

            return productDto;
        };

        return productList.stream().map(productToProductDto).collect(Collectors.toList());
    }
}
