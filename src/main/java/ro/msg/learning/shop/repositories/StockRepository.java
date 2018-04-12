package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ro.msg.learning.shop.entities.Stock;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Integer> {

    @Modifying
    @Query("UPDATE Stock SET  quantity = quantity - :quantityParam WHERE product_id = :productIdParam AND location_id = :locationIdParam")
    void updateStock(@Param("productIdParam") int productId, @Param("quantityParam") int quantity, @Param("locationIdParam") int locationId);

    List<Stock> findAll();
}
