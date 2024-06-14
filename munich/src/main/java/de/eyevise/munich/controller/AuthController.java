package de.eyevise.munich.controller;

import de.eyevise.munich.entity.UserCredential;
import de.eyevise.munich.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping
    public Object createUserCredential(@RequestBody UserCredential userCredential){
        return authService.saveUser(userCredential);

    }

    @GetMapping("/token")
    public String getToken(@RequestBody UserCredential userCredential){
        return authService.generateToken(userCredential.getName());
    }

    @GetMapping("/validate/{token}")
    public String validateToken(@PathVariable String token){
        authService.validateToken(token);
        return "Token is valid";
    }
}
