package ro.msg.learning.shop.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.entities.Revenue;

public interface RevenueRepository extends CrudRepository<Revenue, Integer> {
}
