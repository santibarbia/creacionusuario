package com.creacionusuario.ejercicio.repository;

import com.creacionusuario.ejercicio.utils.TestObjectBuilder;
import com.creacionusuario.entities.Phone;
import com.creacionusuario.repository.PhoneRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class PhoneRepositoryTest {
    @Autowired
    private PhoneRepository phoneRepository;

    private Phone phone;
    @BeforeEach
    void setUp(){
        phone = TestObjectBuilder.getPhone();
    }

    @Test
    void shouldFindById(){
        Phone phoneSave = phoneRepository.save(phone);

        Optional<Phone> result = phoneRepository.findById(phoneSave.getId());

        assertTrue(result.isPresent());
        assertEquals(phoneSave.getNumber(), result.get().getNumber());
        assertEquals(phoneSave.getCityCode(), result.get().getCityCode());
        assertEquals(phoneSave.getCoutryCode(), result.get().getCoutryCode());
    }
}
