package ru.skypro.homework.component.userDetails;

import lombok.Getter;
import ru.skypro.homework.model.User;

import java.util.List;

@Getter
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {

    private final Long id;

    public CustomUserDetails(User user) {
        super(user.getEmail(), user.getPassword(), List.of(user.getRole()));

        this.id = user.getId();
    }

    @Override
    public void eraseCredentials() {
    }

}