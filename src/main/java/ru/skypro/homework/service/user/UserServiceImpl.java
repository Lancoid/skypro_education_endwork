package ru.skypro.homework.service.user;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.ResponseWrapperUserDto;
import ru.skypro.homework.dto.UserCreateDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UserNewPasswordDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserCreateDto create(UserCreateDto userCreateDto) {
        userCreateDto.setFirstName("firstName");
        userCreateDto.setLastName("lastName");
        userCreateDto.setPassword("password");
        userCreateDto.setPhone("phone");
        userCreateDto.setEmail("email");

        return userCreateDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        return getUser();
    }

    @Override
    public UserNewPasswordDto newPassword(UserNewPasswordDto userNewPasswordDto) {
        userNewPasswordDto.setNewPassword("newPassword");
        userNewPasswordDto.setCurrentPassword("currentPassword");

        return userNewPasswordDto;
    }

    @Override
    public UserDto getOneUser(Integer id) {
        return getUser();
    }

    @Override
    public ResponseWrapperUserDto getAllUsers() {
        List<UserDto> list = new ArrayList<>();
        list.add(getUser());

        ResponseWrapperUserDto responseWrapperUserDto = new ResponseWrapperUserDto();
        responseWrapperUserDto.setCount(list.size());
        responseWrapperUserDto.setResults(list);

        return responseWrapperUserDto;
    }

    private UserDto getUser() {
        UserDto userDto = new UserDto();

        userDto.setId(6);
        userDto.setFirstName("firstName");
        userDto.setLastName("lastName");
        userDto.setPhone("phone");
        userDto.setEmail("email");

        return userDto;
    }

}
