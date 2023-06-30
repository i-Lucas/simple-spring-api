package com.example.demo.model;

import java.util.UUID;

import com.example.demo.dto.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Users")
public class UserModel {

    public UserModel(UserDTO request) {

        this.name = request.name();
        this.age = request.age();
        this.email = request.email();
        this.birthdate = request.birthdate();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 10, nullable = false)
    private String birthdate;
}
