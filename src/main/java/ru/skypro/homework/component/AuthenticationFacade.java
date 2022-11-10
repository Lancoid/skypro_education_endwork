package ru.skypro.homework.component;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

    Authentication getAuthentication();

    CustomUserDetails getPrincipal();

    Long getUserId();

}
