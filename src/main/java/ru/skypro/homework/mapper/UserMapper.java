package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.UserCreateDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userCreateDtoToUser(UserCreateDto userCreateDto);

    UserCreateDto userToUserCreateDto(User user);

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);

}
