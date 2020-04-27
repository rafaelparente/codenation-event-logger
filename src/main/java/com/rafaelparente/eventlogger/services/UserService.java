package com.rafaelparente.eventlogger.services;

import com.rafaelparente.eventlogger.dto.UserDTO;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserService {

    Optional<User> createNewUser(UserDTO userDTO);

}
