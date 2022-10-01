package ru.skypro.homework.service.user;

import ru.skypro.homework.dto.CreateUserDto;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.ResponseWrapperUserDto;
import ru.skypro.homework.dto.UserDto;

public interface UserService {

    CreateUserDto create(CreateUserDto createUserDto);

    UserDto update(UserDto userDto);

    NewPasswordDto newPassword(NewPasswordDto newPasswordDto);

    UserDto getOneUser(Integer id);

    ResponseWrapperUserDto getAllUsers();

}
