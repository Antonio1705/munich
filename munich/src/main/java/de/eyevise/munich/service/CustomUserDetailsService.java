package de.eyevise.munich.service;

import de.eyevise.munich.entity.UserCredential;
import de.eyevise.munich.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> byName = userCredentialRepository.findByEmail(username);


        if (byName.isPresent()){
            UserCredential unwrapedUserCredential = byName.get();

            return User
                    .withUsername(unwrapedUserCredential.getEmail())
                    .password(unwrapedUserCredential.getPassword())
                    .roles(unwrapedUserCredential.getRole().toString())
                    .build();

        }
        throw new UsernameNotFoundException("Email not Found:-> "+username);
    }

}
