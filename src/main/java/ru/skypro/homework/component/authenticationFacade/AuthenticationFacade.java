package ru.skypro.homework.component.authenticationFacade;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.component.userDetails.CustomUserDetails;

import java.nio.file.AccessDeniedException;

public interface AuthenticationFacade {

    Authentication getAuthentication();

    CustomUserDetails getPrincipal();

    Long getUserId();

    void isAdminOrOwner(Long ownerId) throws AccessDeniedException;

}
