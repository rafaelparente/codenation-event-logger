package com.rafaelparente.eventlogger.controllers;

import com.rafaelparente.eventlogger.dto.UserDTO;
import com.rafaelparente.eventlogger.exceptions.UserAlreadyExistsException;
import com.rafaelparente.eventlogger.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation("Creates a new user")
    public String processRegister(@ModelAttribute("user") @Valid UserDTO userDto) {
        User createdUser = this.userService.createNewUser(userDto)
                .orElseThrow(() -> new UserAlreadyExistsException(userDto.getUsername()));

        return "Account successfully created! Your account is now ready to use.";
    }

}
