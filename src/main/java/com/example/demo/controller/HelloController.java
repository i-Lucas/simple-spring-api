package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/")
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserModel> getUsers() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO request) {

        userService.createUser(request);
        return ResponseEntity.ok("User created successfully.");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable UUID userId, @RequestBody UserDTO userDto) {

        userService.saveUser(userDto, userId);
        return ResponseEntity.ok("User updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {

        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

}
