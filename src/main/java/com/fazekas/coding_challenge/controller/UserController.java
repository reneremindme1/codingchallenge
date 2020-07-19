package com.fazekas.coding_challenge.controller;

import com.fazekas.coding_challenge.exception.ResourceNotFoundException;
import com.fazekas.coding_challenge.model.User;
import com.fazekas.coding_challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping(value = "/users")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUser(
            @PathVariable Long id
    ) throws ResourceNotFoundException {
        return ResponseEntity.ok(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found "+id)));
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        return ResponseEntity.ok(userRepository.save(newUser));
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User newUser) throws ResourceNotFoundException {

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found "+id));

        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setGitHubProfileUrl(newUser.getGitHubProfileUrl());
        user.setPosition(newUser.getPosition());
        return ResponseEntity.ok(userRepository.save(user));
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id
    ){
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
