package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.handlers.UserNotFoundException;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel createUser(UserDTO user) {
        return userRepository.save(new UserModel(user));
    }

    public UserModel findUserById(UUID id) {

        Optional<UserModel> findUserModel = userRepository.findById(id);

        if (!findUserModel.isPresent()) {
            throw new UserNotFoundException("User not found.");
        }

        return findUserModel.get();
    }

    public void saveUser(UserDTO userDto, UUID userId) {

        UserModel newUserModel = this.findUserById(userId);
        BeanUtils.copyProperties(userDto, newUserModel, "id"); // Copia as propriedades, exceto "id"
        userRepository.save(newUserModel);
    }

    public void deleteUserById(UUID id) {

        UserModel findUserModel = this.findUserById(id);
        if (findUserModel != null) {
            userRepository.deleteById(id);
        }
    }
}
