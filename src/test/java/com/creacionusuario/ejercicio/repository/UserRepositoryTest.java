package com.creacionusuario.ejercicio.repository;

import com.creacionusuario.ejercicio.utils.TestObjectBuilder;
import com.creacionusuario.entities.User;
import com.creacionusuario.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp(){
        user = TestObjectBuilder.getUser();
    }

    @Test
    void shouldFindUserById(){
        User savedUser = userRepository.save(user);

        Optional<User> result = userRepository.findById(UUID.fromString("c17c1d11-726d-4b9a-a8c0-0a2e5cf4c032"));
        assertTrue(result.isPresent());
        assertEquals(savedUser.getEmail(), result.get().getEmail());
        assertEquals(savedUser.getName(), result.get().getName());
        assertEquals(savedUser.getPassword(), result.get().getPassword());
        assertEquals(savedUser.getCreated(), result.get().getCreated());

    }
}
