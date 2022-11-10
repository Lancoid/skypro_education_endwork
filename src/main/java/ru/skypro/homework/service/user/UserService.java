package ru.skypro.homework.service.user;

import ru.skypro.homework.dto.UserCreateDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UserNewPasswordDto;

public interface UserService {

    void create(UserCreateDto userCreateDto);

    UserDto update(UserDto userDto);

    UserNewPasswordDto newPassword(UserNewPasswordDto userNewPasswordDto);

    UserDto getOneUser(Integer id);

    UserDto getMe();

}
