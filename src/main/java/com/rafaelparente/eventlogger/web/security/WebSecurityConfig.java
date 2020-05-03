package com.rafaelparente.eventlogger.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableAuthorizationServer
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private JdbcUserDetailsManager jdbcUserDetailsManager;

    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {
        if (jdbcUserDetailsManager == null)
        {
            jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        }
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() throws Exception {
        if (passwordEncoder == null)
        {
            passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }
        return passwordEncoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .withDefaultSchema();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/account/register*").anonymous()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().ignoringAntMatchers("/account/register*").and()
                .formLogin()
                .loginProcessingUrl("/oauth/authorize")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/swagger-ui.html")
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/")
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**");
    }

}
