package ru.skypro.homework.component;

import org.jetbrains.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

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

}