package com.example.demo.service;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.handlers.UserNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public Page<UserModel> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public UserModel createUser(UserDTO user) {
        return userRepository.save(new UserModel(user));
    }

    public UserModel findUserById(UUID userId) {

        Optional<UserModel> findUserModel = userRepository.findById(userId);

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
