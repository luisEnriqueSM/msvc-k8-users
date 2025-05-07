package org.k8.springcloud.msvc.users.repositories;

import org.k8.springcloud.msvc.users.models.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

}
