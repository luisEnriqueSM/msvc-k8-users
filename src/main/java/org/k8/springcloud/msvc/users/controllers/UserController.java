package org.k8.springcloud.msvc.users.controllers;

import java.util.Optional;

import org.k8.springcloud.msvc.users.models.entities.User;
import org.k8.springcloud.msvc.users.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> list(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return userService.findByUserId(id).map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id){
        Optional<User> optaionalUser = userService.findByUserId(id);
        if (optaionalUser.isPresent()) {
            User userDB = optaionalUser.get();
            userDB.setName(user.getName());
            userDB.setEmail(user.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDB));    
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return userService.findByUserId(id)
            .map(user -> {
                userService.delete(id);
                return ResponseEntity.noContent().build();
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
