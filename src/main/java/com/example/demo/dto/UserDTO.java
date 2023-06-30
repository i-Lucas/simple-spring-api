package com.example.demo.dto;

public record UserDTO(String name, int age, String email, String birthdate) {

    // public String name() {
    //     return "name: "+ this.name;
    // }
}