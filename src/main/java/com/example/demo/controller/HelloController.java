package com.example.demo.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.UserDTO;
import com.example.demo.handlers.exceptions.InvalidUUID;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class HelloController {

    @Autowired
    private UserService userService;
    private UUID uuId;

    @GetMapping
    public Page<UserModel> getUsers(@PageableDefault(page = 0, size = 10) Pageable page) {
        return userService.findAll(page);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> findUserById(@PathVariable String userId) throws InvalidUUID {

        try {
            uuId = UUID.fromString(userId);
            
        } catch (IllegalArgumentException e) {
            throw new InvalidUUID();
        }

        UserModel user = userService.findUserById(uuId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok("User created successfully.");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable UUID userId, @RequestBody UserDTO userDto) {
        userService.updateUser(userDto, userId);
        return ResponseEntity.ok("User updated successfully.");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }

}
