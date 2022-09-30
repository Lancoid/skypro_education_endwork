package ru.skypro.homework.service.user;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateUser;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.ResponseWrapperUser;
import ru.skypro.homework.dto.User;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public CreateUser create(CreateUser user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public NewPassword newPassword(NewPassword newPassword) {
        return null;
    }

    @Override
    public User getOneUser(Integer id) {
        return null;
    }

    @Override
    public ResponseWrapperUser getAllUsers() {
        return null;
    }

}
