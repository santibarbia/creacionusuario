package com.creacionusuario.ejercicio.mapper;

import com.creacionusuario.ejercicio.utils.TestObjectBuilder;
import com.creacionusuario.entities.Phone;
import com.creacionusuario.entities.User;
import com.creacionusuario.mapper.PhoneMapper;
import com.creacionusuario.models.PhoneModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneMapperTest {

    private PhoneMapper phoneMapper;
    private Phone phone;
    private PhoneModel phoneModel;

    @BeforeEach
    void setUp() {
        phoneMapper = new PhoneMapper();
        phone = TestObjectBuilder.getPhone();
        phoneModel = TestObjectBuilder.getPhoneModel();
    }

    @Test
    void shouldMapPhoneSuccessfull() {
        User user = new User();

        Phone phoneEntity = phoneMapper.mapToPhoneRecord(phoneModel, user);
        assertEquals(user, phoneEntity.getUser());
        assertEquals(12, phoneEntity.getCoutryCode());
        assertEquals(57, phoneEntity.getCityCode());
        assertEquals(12345, phoneEntity.getNumber());
    }

    @Test
    void shouldMapPhoneModelSuccessfull() {
        PhoneModel response = phoneMapper.mapToPhoneUser(phone);


        assertEquals("12", response.getCountryCode());
        assertEquals("57", response.getCityCode());
        assertEquals("12345", response.getNumber());
    }
}
