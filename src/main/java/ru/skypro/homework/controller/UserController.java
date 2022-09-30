package ru.skypro.homework.controller;

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
import ru.skypro.homework.dto.CreateUser;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.ResponseWrapperUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.user.UserService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(
            value = "addUser",
            nickname = "addUserUsingPOST",
            notes = "",
            response = CreateUser.class,
            tags = {"Пользователи"}
    )
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateUser.class))),
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PostMapping
    public ResponseEntity<CreateUser> _addUserUsingPOST(
            @ApiParam(value = "user", required = true) @Valid @RequestBody CreateUser user
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.create(user));
    }

    @ApiOperation(
            value = "updateUser",
            nickname = "updateUserUsingPATCH",
            notes = "",
            response = User.class,
            tags = {"Пользователи"}
    )
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PatchMapping(path = "me")
    public ResponseEntity<User> _updateUserUsingPATCH(
            @ApiParam(value = "user", required = true) @Valid @RequestBody User user
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(user));
    }

    @ApiOperation(
            value = "setPassword",
            nickname = "setPasswordUsingPOST",
            notes = "",
            response = NewPassword.class,
            tags = {"Пользователи"}
    )
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = NewPassword.class))),
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PostMapping(path = "set_password")
    public ResponseEntity<NewPassword> _setPasswordUsingPOST(
            @ApiParam(value = "newPassword", required = true) @Valid @RequestBody NewPassword newPassword
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.newPassword(newPassword));
    }

    @ApiOperation(
            value = "getUser",
            nickname = "getUserUsingGET",
            notes = "",
            response = User.class,
            tags = {"Пользователи"}
    )
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(path = "{id}")
    public ResponseEntity<User> _getUserUsingGET(
            @ApiParam(value = "id", required = true) @PathVariable("id") Integer id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getOneUser(id));
    }

    @ApiOperation(
            value = "getUsers",
            nickname = "getUsersUsingGET",
            notes = "",
            response = ResponseWrapperUser.class,
            tags = {"Пользователи"}
    )
    @ApiResponses(value = {
            @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapperUser.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping(path = "me")
    public ResponseEntity<ResponseWrapperUser> _getUsersUsingGET() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

}
