package ru.skypro.homework.component;

import org.springframework.security.core.Authentication;

import java.nio.file.AccessDeniedException;

public interface AuthenticationFacade {

    Authentication getAuthentication();

    CustomUserDetails getPrincipal();

    Long getUserId();

    void isAdminOrOwner(Long ownerId) throws AccessDeniedException;

}
