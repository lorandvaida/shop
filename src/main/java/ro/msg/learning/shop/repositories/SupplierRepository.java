package ro.msg.learning.shop.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.entities.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Integer> {
}
