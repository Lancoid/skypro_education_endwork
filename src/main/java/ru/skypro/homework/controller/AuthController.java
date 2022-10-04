package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserLoginDto;
import ru.skypro.homework.dto.UserRegisterDto;
import ru.skypro.homework.service.auth.AuthService;

import static ru.skypro.homework.dto.Role.USER;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        if (authService.login(userLoginDto.getUsername(), userLoginDto.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto userRegisterDto) {
        Role role = userRegisterDto.getRole() == null ? USER : userRegisterDto.getRole();
        if (authService.register(userRegisterDto, role)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
