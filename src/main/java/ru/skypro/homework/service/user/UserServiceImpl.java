package ru.skypro.homework.service.user;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateUser;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.ResponseWrapperUser;
import ru.skypro.homework.dto.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public CreateUser create(CreateUser user) {
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPassword("password");
        user.setPhone("phone");
        user.setEmail("email");

        return user;
    }

    @Override
    public User update(User user) {
        return getUser();
    }

    @Override
    public NewPassword newPassword(NewPassword newPassword) {
        newPassword.setNewPassword("newPassword");
        newPassword.setCurrentPassword("currentPassword");

        return newPassword;
    }

    @Override
    public User getOneUser(Integer id) {
        return getUser();
    }

    @Override
    public ResponseWrapperUser getAllUsers() {
        List<User> list = new ArrayList<>();
        list.add(getUser());

        ResponseWrapperUser responseWrapperUser = new ResponseWrapperUser();
        responseWrapperUser.setCount(list.size());
        responseWrapperUser.setResults(list);

        return responseWrapperUser;
    }

    private User getUser() {
        User user = new User();

        user.setId(6);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPhone("phone");
        user.setEmail("email");

        return user;
    }

}
