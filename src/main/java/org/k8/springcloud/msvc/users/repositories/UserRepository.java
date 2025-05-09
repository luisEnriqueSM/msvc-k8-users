package org.k8.springcloud.msvc.users.repositories;

import java.util.Optional;

import org.k8.springcloud.msvc.users.models.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.email=?1")
    Optional<User> findByEmail2(String email);

    boolean existsByEmail(String email);
}
