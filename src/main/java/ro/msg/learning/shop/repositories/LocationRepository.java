package ro.msg.learning.shop.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.entities.Location;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Integer> {

    List<Location> findAll();
}
