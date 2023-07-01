package com.example.demo.repository;

import java.util.UUID;
import com.example.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

}
