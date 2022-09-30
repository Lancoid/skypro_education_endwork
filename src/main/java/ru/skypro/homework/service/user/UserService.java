package ru.skypro.homework.service.user;

import ru.skypro.homework.dto.CreateUser;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.ResponseWrapperUser;
import ru.skypro.homework.dto.User;

public interface UserService {

    CreateUser create(CreateUser user);

    User update(User user);

    NewPassword newPassword(NewPassword newPassword);

    User getOneUser(Integer id);

    ResponseWrapperUser getAllUsers();

}
