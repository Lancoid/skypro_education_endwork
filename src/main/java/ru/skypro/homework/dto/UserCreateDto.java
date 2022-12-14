package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.type.RoleType;

@Data
public class UserCreateDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private RoleType roleType;

}
