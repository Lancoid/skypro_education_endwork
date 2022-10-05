package ru.skypro.homework.service.auth;

import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserRegisterDto;

public interface AuthService {

    boolean login(String userName, String password);

    boolean register(UserRegisterDto userRegisterDto, Role role);

}
