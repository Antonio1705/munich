package de.eyevise.munich.config;

import de.eyevise.munich.entity.UserCredential;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String name;
    private String password;
    private String role;

    public CustomUserDetails(UserCredential userCredential) {
        this.name = userCredential.getName();
        this.password = userCredential.getPassword();
        this.role = userCredential.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> rolles = List.of("user,admin");
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

        for (String roll: rolles){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(roll));
        }

        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }
}
