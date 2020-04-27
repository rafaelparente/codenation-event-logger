package com.rafaelparente.eventlogger.controllers;

import com.rafaelparente.eventlogger.dto.UserDTO;
import com.rafaelparente.eventlogger.exceptions.UserAlreadyExistsException;
import com.rafaelparente.eventlogger.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation("Creates a new user")
    public ModelAndView processRegister(@ModelAttribute("user") @Valid UserDTO userDto,
                                        HttpServletRequest request, Errors errors) {
        User createdUser = this.userService.createNewUser(userDto)
                .orElseThrow(() -> new UserAlreadyExistsException(userDto.getUsername()));

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
        authToken.setDetails(new WebAuthenticationDetails(request));

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ModelAndView("redirect:/oauth/token");
    }

}
