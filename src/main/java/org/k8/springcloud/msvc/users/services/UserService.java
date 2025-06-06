package org.k8.springcloud.msvc.users.services;

import java.util.List;
import java.util.Optional;

import org.k8.springcloud.msvc.users.models.entities.User;

public interface UserService {

    Iterable<User>findAll();
    Optional<User> findByUserId(Long id);
    User save(User user);
    void delete(Long id);
    List<User> findAllById(Iterable<Long> ids);

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
