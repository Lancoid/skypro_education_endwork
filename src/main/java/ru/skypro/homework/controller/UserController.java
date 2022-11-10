package ru.skypro.homework.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.ResponseWrapperUserDto;
import ru.skypro.homework.dto.UserCreateDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UserNewPasswordDto;
import ru.skypro.homework.service.user.UserService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(tags = {"Пользователи"})
public class UserController {

    private final UserService userService;

    @ApiOperation(
            value = "addUser",
            nickname = "addUserUsingPOST",
            notes = "Создание пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserCreateDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PostMapping
    public ResponseEntity<UserCreateDto> addUser(
            @ApiParam(value = "user", required = true) @Valid @RequestBody UserCreateDto userCreateDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreateDto);
    }

    @ApiOperation(
            value = "updateUser",
            nickname = "updateUserUsingPATCH",
            notes = "Редактирование пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PatchMapping(path = "me")
    public ResponseEntity<UserDto> updateUser(
            @ApiParam(value = "user", required = true) @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userDto));
    }

    @ApiOperation(
            value = "setPassword",
            nickname = "setPasswordUsingPOST",
            notes = "Изменение пароля пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserNewPasswordDto.class))),
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PostMapping(path = "set_password")
    public ResponseEntity<UserNewPasswordDto> setPassword(
            @ApiParam(value = "newPassword", required = true) @Valid @RequestBody UserNewPasswordDto userNewPasswordDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.newPassword(userNewPasswordDto));
    }

    @ApiOperation(
            value = "getUser",
            nickname = "getUserUsingGET",
            notes = "Получение одного пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(path = "{id}")
    public ResponseEntity<UserDto> getUser(
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getOneUser(id));
    }

    @ApiOperation(
            value = "getUsers",
            nickname = "getUsersUsingGET",
            notes = "Получение всех пользователей"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseWrapperUserDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(path = "me")
    public ResponseEntity<UserDto> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getMe());
    }

}
