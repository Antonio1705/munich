package de.eyevise.munich.service;

import de.eyevise.munich.config.CustomUserDetails;
import de.eyevise.munich.entity.UserCredential;
import de.eyevise.munich.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> byName = userCredentialRepository.findByName(username);

        return byName.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found with username: "+username));
    }

    /*
    @Bean
    public UserDetailsService users() {

        User.UserBuilder users = User.withDefaultPasswordEncoder();

        UserDetails user = users
                .username("user")
                .password("password")
                .roles("USER")
                .authorities("READ")
                .build();

        UserDetails admin = users
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .authorities("READ", "CREATE", "DELETE")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }*/
}
