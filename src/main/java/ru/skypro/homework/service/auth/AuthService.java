package ru.skypro.homework.service.auth;

import ru.skypro.homework.dto.UserCreateDto;

public interface AuthService {

    void login(String userName, String password);

    boolean register(UserCreateDto userRegisterDto);

}
