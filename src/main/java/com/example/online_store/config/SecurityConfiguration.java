package com.example.online_store.config;

import com.example.online_store.model.enums.UserRoleEnum;
import com.example.online_store.repo.UserRepository;
import com.example.online_store.service.impl.OnlineStoreUserDetailsService;
import com.example.online_store.service.oauth.OAuthSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    private final String rememberMeKey;

    public SecurityConfiguration(
            @Value("${online_shop_store.remember.me.key}") String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity,
                                           OAuthSuccessHandler oAuthSuccessHandler) throws Exception {
        return httpSecurity.authorizeHttpRequests(

                authorizeRequests -> authorizeRequests

                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

                        .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        .requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll()
                        .requestMatchers("/offers/all").permitAll()
                        .requestMatchers("/api/currency/convert").permitAll()
                        .requestMatchers(HttpMethod.GET, "/offer/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/brands").hasRole(UserRoleEnum.ADMIN.name())

                        .anyRequest().authenticated()
        ).formLogin(
                formLogin -> {
                    formLogin

                            .loginPage("/users/login")

                            .usernameParameter("email")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/")
                            .failureForwardUrl("/users/login-error");
                }
        ).logout(
                logout -> {
                    logout

                            .logoutUrl("/users/logout")

                            .logoutSuccessUrl("/")

                            .invalidateHttpSession(true);
                }
        ).rememberMe(
                rememberMe ->
                        rememberMe
                                .key(rememberMeKey)
                                .rememberMeParameter("rememberme")
                                .rememberMeCookieName("rememberme")
        ).oauth2Login(
                oauth -> oauth.successHandler(oAuthSuccessHandler)
        ).build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {

        return new OnlineStoreUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

}