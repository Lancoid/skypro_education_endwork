package ru.skypro.homework.service.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserCreateDto create(UserCreateDto userCreateDto) {
        User user = UserMapper.INSTANCE.userCreateDtoToUser(userCreateDto);

        user.setPassword(encoder.encode(userCreateDto.getPassword()));
        user.setRole(Role.USER);

        user = userRepository.save(user);

        return UserMapper.INSTANCE.userToUserCreateDto(user);
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        user.setId(1L);

        user = userRepository.save(user);

        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public UserNewPasswordDto newPassword(UserNewPasswordDto userNewPasswordDto) {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("id: 1"));

        if (!encoder.matches(userNewPasswordDto.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Неверно введённый текущий пароль");
        }

        user.setPassword(encoder.encode(userNewPasswordDto.getNewPassword()));
        userRepository.save(user);

        return userNewPasswordDto;
    }

    @Override
    public UserDto getOneUser(Integer id) {
        User user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("id: " + id));

        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public ResponseWrapperUserDto getAllUsers() {
        List<User> list = userRepository.findAll();

        ResponseWrapperUserDto responseWrapperUserDto = new ResponseWrapperUserDto();

        for (User user : list) {
            responseWrapperUserDto.setOneDto(UserMapper.INSTANCE.userToUserDto(user));
        }

        return responseWrapperUserDto;
    }

}
