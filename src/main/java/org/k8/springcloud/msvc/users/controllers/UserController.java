package org.k8.springcloud.msvc.users.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.k8.springcloud.msvc.users.models.entities.User;
import org.k8.springcloud.msvc.users.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class UserController {

    private UserService userService;
    private ApplicationContext context;
    private Environment env;

    public UserController(UserService userService, ApplicationContext context, Environment environment) {
        this.userService = userService;
        this.context = context;
        this.env = environment;
    }

    @GetMapping("/crash")
    public void crash(){
        ((ConfigurableApplicationContext)context).close();
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> list(){
        Map<String, Object> body = new HashMap<>();
        body.put("users", userService.findAll());
        body.put("pod_Info", env.getProperty("MY_POD_NAME") + " : " + env.getProperty("MY_POD_IP"));
        body.put("texto", env.getProperty("config.texto"));
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return userService.findByUserId(id).map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            return getErrors(result);
        }
        if(!user.getEmail().isEmpty() && userService.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body(
                Collections.singletonMap("mensaje", "El usuario ya existe con el corre electronico!"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            return getErrors(result);
        }
        Optional<User> optaionalUser = userService.findByUserId(id);
        if (optaionalUser.isPresent()) {
            User userDB = optaionalUser.get();
            if(!user.getEmail().isEmpty() && !user.getEmail().equalsIgnoreCase(userDB.getEmail()) 
                    && userService.findByEmail(user.getEmail()).isPresent()){
                return ResponseEntity.badRequest().body(
                    Collections.singletonMap("mensaje", "El usuario ya existe con el corre electronico!"));
            }
            userDB.setName(user.getName());
            userDB.setEmail(user.getEmail());
            userDB.setPassword(user.getPassword());
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

    @GetMapping("/usuarios-por-curso")
    public ResponseEntity<?> getUsersByCourse(@RequestParam List<Long> usersIds){
        return ResponseEntity.ok().body(userService.findAllById(usersIds));
    }

    private ResponseEntity<?> getErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
