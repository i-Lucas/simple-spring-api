package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
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
import com.example.demo.handlers.UserNotFoundException;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/")
public class HelloController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserModel> hello() {
        return userRepository.findAll();
    }

    @PostMapping
    public void createUser(@RequestBody UserDTO request) {
        System.out.println(request.name());
        userRepository.save(new UserModel(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @RequestBody UserDTO userDto) {
        Optional<UserModel> findUserModel = userRepository.findById(id);

        if (!findUserModel.isPresent()) {
            throw new UserNotFoundException("User not found.");
        }

        UserModel userModel = findUserModel.get();
        BeanUtils.copyProperties(userDto, userModel, "id"); // Copia as propriedades, excluindo a propriedade "id"

        userRepository.save(userModel);
        return ResponseEntity.ok("User updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        Optional<UserModel> findUser = userRepository.findById(id);

        if (!findUser.isPresent()) {
            throw new UserNotFoundException("User not found.");
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted successfully."); // 200 OK
    }

}
