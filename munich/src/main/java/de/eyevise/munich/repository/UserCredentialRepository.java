package de.eyevise.munich.repository;

import de.eyevise.munich.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository  extends JpaRepository<UserCredential,Integer> {

    Optional<UserCredential> findByEmail(String email);




}
