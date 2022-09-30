package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateUser {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;

}
