package com.rafaelparente.eventlogger.services;

import com.rafaelparente.eventlogger.dto.UserDTO;
import com.rafaelparente.eventlogger.facades.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
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

    @Autowired
    private JdbcTokenStore tokenStore;

    @Autowired
    private SessionRegistry sessionRegistry;

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

        for (OAuth2AccessToken oAuth2AccessToken : this.tokenStore.findTokensByUserName(username)) {
            this.tokenStore.removeRefreshToken(oAuth2AccessToken.getRefreshToken());
            this.tokenStore.removeAccessToken(oAuth2AccessToken);
        }

        for (SessionInformation session : sessionRegistry.getAllSessions(authenticationFacade.getAuthentication().getPrincipal(), false)) {
            session.expireNow();
        }
    }

}
