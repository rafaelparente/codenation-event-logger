package com.rafaelparente.eventlogger.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static class BearerRequestMatcher implements RequestMatcher {
        @Override
        public boolean matches(HttpServletRequest request) {
            String auth = request.getHeader("Authorization");
            return (auth != null && auth.startsWith("Bearer"));
        }
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatcher(new BearerRequestMatcher())
                .authorizeRequests()
                .antMatchers("/error").permitAll()
                .antMatchers("/login*", "/logout*", "/account/register*").denyAll()
                .anyRequest().authenticated();
    }

}
