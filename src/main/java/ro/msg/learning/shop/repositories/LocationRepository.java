package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.OrderDetail;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Integer> {
}
