package ru.skypro.homework.component;

import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Role;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Nullable
    @Override
    public final Authentication getAuthentication() {
        if (null == SecurityContextHolder.getContext()) {
            return null;
        }

        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Nullable
    @Override
    public final CustomUserDetails getPrincipal() {
        if (null == getAuthentication()) {
            return null;
        }

        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public Long getUserId() {
        CustomUserDetails customUserDetails = getPrincipal();

        if (null != customUserDetails) {
            return customUserDetails.getId();
        }

        return null;
    }

    @Override
    public void isAdminOrOwner(Long ownerId) throws AccessDeniedException {
        CustomUserDetails userDetails = getPrincipal();

        if (null == userDetails) {
            throw new AccessDeniedException("Доступ закрыт");
        }

        if (!userDetails.getAuthorities().contains(Role.ADMIN) && !Objects.equals(ownerId, userDetails.getId())) {
            throw new AccessDeniedException("Доступ закрыт");
        }
    }

}