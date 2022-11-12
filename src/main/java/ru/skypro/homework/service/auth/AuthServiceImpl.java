package ru.skypro.homework.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserCreateDto;
import ru.skypro.homework.service.user.UserService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public boolean login(String userName, String password) {
        UserDetails user = userDetailsService.loadUserByUsername(userName);

        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public boolean register(UserCreateDto userCreateDto) {
        return userService.create(userCreateDto);
    }

}
