package ro.msg.learning.shop.repositories;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.entities.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {}