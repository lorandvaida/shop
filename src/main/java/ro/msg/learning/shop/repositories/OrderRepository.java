package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findAll();

    @Query("SELECT sum(od.quantity * p.price) FROM OrderDetail od JOIN od.order o JOIN od.product p where o.shippedFrom = :locationParam AND o.orderDate = current_date ")
    BigDecimal getRevenueSum(@Param("locationParam") Location location);

}
