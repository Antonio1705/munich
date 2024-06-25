package de.eyevise.munich.service;

import de.eyevise.munich.entity.UserCredential;
import de.eyevise.munich.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) {
        try {
            credential.setPassword(passwordEncoder.encode(credential.getPassword()));
            repository.save(credential);
            return "user added to the system";
        }catch (Exception e){
            throw new RuntimeException("Server error SaveUser");
        }

    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public boolean hasRole(){
        return true;
    }
}