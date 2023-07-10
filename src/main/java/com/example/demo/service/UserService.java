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
import com.example.demo.handlers.exceptions.UserNotFound;
import com.example.demo.handlers.exceptions.EmailAlreadyRegistered;

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

    public UserModel findUserByEmail(String email) {

        UserModel user = userRepository.findByEmail(email);

        if (user != null) {
            throw new EmailAlreadyRegistered();
        }
        return user;
    }

    public UserModel createUser(UserDTO newUser) {

        UserModel user = userRepository.findByEmail(newUser.email());

        if (user != null) {
            throw new EmailAlreadyRegistered();
        }
        return userRepository.save(new UserModel(newUser));
    }

    public UserModel findUserById(UUID userId) {

        Optional<UserModel> findUserModel = userRepository.findById(userId);

        if (!findUserModel.isPresent()) {
            throw new UserNotFound();
        }

        return findUserModel.get();
    }

    public void updateUser(UserDTO userDto, UUID userId) {

        UserModel newUserModel = this.findUserById(userId);
        BeanUtils.copyProperties(userDto, newUserModel, "id");
        userRepository.save(newUserModel);
    }

    public void deleteUserById(UUID userId) {

        Optional<UserModel> findUserModel = userRepository.findById(userId);

        if (!findUserModel.isPresent()) {
            throw new UserNotFound();
        }

        userRepository.deleteById(userId);
    }
}
