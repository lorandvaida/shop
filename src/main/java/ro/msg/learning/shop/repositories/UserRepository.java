package ro.msg.learning.shop.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
