package ro.msg.learning.shop.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.entities.Stock;

public interface StockRepository extends CrudRepository<Stock, Integer> {
}
