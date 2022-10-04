package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UserNewPasswordDto {

    private String currentPassword;
    private String newPassword;

}
