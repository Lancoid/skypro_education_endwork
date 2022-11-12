package ru.skypro.homework.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.component.AuthenticationFacade;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserCreateDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UserNewPasswordDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder encoder,
            AuthenticationFacade authenticationFacade
    ) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public boolean create(UserCreateDto userCreateDto) {
        User user = UserMapper.INSTANCE.userCreateDtoToUser(userCreateDto);

        if (userRepository.findByEmailEqualsIgnoreCase(user.getEmail()).isPresent()) {
            return false;
        }

        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        user.setPassword(encoder.encode(userCreateDto.getPassword()));

        user = userRepository.save(user);

        return null != user.getId();
    }

    @Override
    public UserDto update(UserDto userDto) {
        if (null == userDto.getId()) {
            Long userId = authenticationFacade.getUserId();

            if (null == userId) {
                throw new RuntimeException("Ошибка определения пользователя");
            }

            userDto.setId(userId.intValue());
        }

        User user = userRepository.findById(userDto.getId().longValue())
                .orElseThrow(() -> new EntityNotFoundException("id: " + userDto.getId()));

        if (null != userDto.getEmail()) {
            user.setEmail(userDto.getEmail());
        }

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());

        user = userRepository.save(user);

        return UserMapper.INSTANCE.userToUserDto(user);
    }

    @Override
    public UserNewPasswordDto newPassword(UserNewPasswordDto userNewPasswordDto) {
        Long userId = authenticationFacade.getUserId();

        if (null == userId) {
            throw new RuntimeException("Ошибка определения пользователя");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("id: " + userId));

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
    public UserDto getMe() {
        Long userId = authenticationFacade.getUserId();

        if (null == userId) {
            throw new RuntimeException("Ошибка определения пользователя");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("id: " + userId));

        return UserMapper.INSTANCE.userToUserDto(user);
    }

}
