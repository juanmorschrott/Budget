package com.habitissimo.backend.controller;

import com.habitissimo.backend.exception.ResourceNotFoundException;
import com.habitissimo.backend.model.User;
import com.habitissimo.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path="/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping()
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        return userRepository.findById(id).map(post -> {
            post.setEmail(user.getEmail());
            post.setAddress(user.getAddress());
            post.setPhone(user.getPhone());
            return userRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId: " + id + " not found"));
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long postId) {
        return userRepository.findById(postId).map(post -> {
            userRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId: " + postId + " not found"));
    }

}
