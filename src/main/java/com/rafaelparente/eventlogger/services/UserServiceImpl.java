package com.rafaelparente.eventlogger.services;

import com.rafaelparente.eventlogger.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Optional<User> createNewUser(UserDTO userDTO) {
        if (jdbcUserDetailsManager.userExists(userDTO.getUsername())) {
            return Optional.empty();
        }

        User user = new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        jdbcUserDetailsManager.createUser(user);

        return Optional.of(user);
    }

}
