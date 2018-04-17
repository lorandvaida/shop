package ro.msg.learning.shop.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.entities.OrderDetail;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {
}
