package ro.msg.learning.shop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    Page<Product> findAll(Pageable pageable);

}
