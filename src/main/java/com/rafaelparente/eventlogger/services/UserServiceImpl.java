package com.rafaelparente.eventlogger.services;

import com.rafaelparente.eventlogger.dto.UserDTO;
import com.rafaelparente.eventlogger.facades.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private EventService eventService;

    @Transactional
    @Override
    public Optional<User> createNewUser(UserDTO userDTO) {
        if (this.jdbcUserDetailsManager.userExists(userDTO.getUsername())) {
            return Optional.empty();
        }

        User user = new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        this.jdbcUserDetailsManager.createUser(user);

        return Optional.of(user);
    }

    @Transactional
    @Override
    public void delete() {
        String username = authenticationFacade.getAuthentication().getName();
        this.eventService.deleteAll(username);
        this.jdbcUserDetailsManager.deleteUser(username);
    }

}
