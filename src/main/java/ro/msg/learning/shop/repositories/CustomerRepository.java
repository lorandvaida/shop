package ro.msg.learning.shop.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
