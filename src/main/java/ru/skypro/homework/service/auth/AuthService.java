package ru.skypro.homework.service.auth;

import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;

public interface AuthService {

    boolean login(String userName, String password);

    boolean register(RegisterReqDto registerReqDto, Role role);

}
