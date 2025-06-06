package org.k8.springcloud.msvc.users.services;

import java.util.List;
import java.util.Optional;

import org.k8.springcloud.msvc.users.clients.CourseClientRest;
import org.k8.springcloud.msvc.users.models.entities.User;
import org.k8.springcloud.msvc.users.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private CourseClientRest courseClientRest;

    public UserServiceImpl(UserRepository userRepository, CourseClientRest courseClientRest) {
        this.userRepository = userRepository;
        this.courseClientRest = courseClientRest;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
       return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUserId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
        courseClientRest.deleteCourseUser(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail2(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllById(Iterable<Long> ids) {
        return (List<User>) userRepository.findAllById(ids);
    }

    
}
