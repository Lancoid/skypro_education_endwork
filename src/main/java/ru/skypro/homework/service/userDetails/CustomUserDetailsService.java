package ru.skypro.homework.service.userDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.component.userDetails.CustomUserDetails;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

@Transactional
@Service
public class CustomUserDetailsService implements UserDetailsService, UserDetailsPasswordService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);

        return new CustomUserDetails(user);
    }

    @Override
    public UserDetails updatePassword(UserDetails userDetails, String newPassword) {
        User user = getUserByUsername(userDetails.getUsername());

        user.setPassword(newPassword);

        CustomUserDetails updatedUserDetails = new CustomUserDetails(userRepository.save(user));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(updatedUserDetails, null, updatedUserDetails.getAuthorities())
        );

        return updatedUserDetails;
    }

    private User getUserByUsername(String username) {
        return userRepository.findByEmailEqualsIgnoreCase(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Пользователь с email: \"%s\" не найден", username)));
    }

}