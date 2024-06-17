package de.eyevise.munich.controller;

import de.eyevise.munich.dto.AuthRequestDto;
import de.eyevise.munich.entity.UserCredential;
import de.eyevise.munich.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public Object createUserCredential(@RequestBody UserCredential userCredential){
        return authService.saveUser(userCredential);

    }

    @GetMapping("/token")
    public String getToken(@RequestBody AuthRequestDto authRequestDto){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getName(), authRequestDto.getPassword()));
        if (authenticate.isAuthenticated()){
            return authService.generateToken(authRequestDto.getName());
        }
        throw new RuntimeException("invalid access");

    }

    @GetMapping("/validate/{token}")
    public String validateToken(@PathVariable String token){

        authService.validateToken(token);
        return "Token is valid";
    }

    @GetMapping("/testuser")
    public String testRole(){
        return "Hello user";
    }
}
