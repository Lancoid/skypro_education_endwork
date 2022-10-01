package ru.skypro.homework.service.user;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateUserDto;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.ResponseWrapperUserDto;
import ru.skypro.homework.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public CreateUserDto create(CreateUserDto createUserDto) {
        createUserDto.setFirstName("firstName");
        createUserDto.setLastName("lastName");
        createUserDto.setPassword("password");
        createUserDto.setPhone("phone");
        createUserDto.setEmail("email");

        return createUserDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        return getUser();
    }

    @Override
    public NewPasswordDto newPassword(NewPasswordDto newPasswordDto) {
        newPasswordDto.setNewPassword("newPassword");
        newPasswordDto.setCurrentPassword("currentPassword");

        return newPasswordDto;
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
