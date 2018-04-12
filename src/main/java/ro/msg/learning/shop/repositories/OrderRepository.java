package ro.msg.learning.shop.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.entities.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findAll();
}
