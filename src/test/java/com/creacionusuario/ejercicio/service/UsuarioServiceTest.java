package com.creacionusuario.ejercicio.service;

import com.creacionusuario.controller.contract.ActualizacionUsuarioContract;
import com.creacionusuario.controller.contract.RegistroUsuarioContract;
import com.creacionusuario.controller.contract.ResponseRegistroContract;
import com.creacionusuario.ejercicio.utils.TestObjectBuilder;
import com.creacionusuario.entities.User;
import com.creacionusuario.exception.ApiException;
import com.creacionusuario.exception.EmailExisteException;
import com.creacionusuario.mapper.PhoneMapper;
import com.creacionusuario.mapper.UsuarioMapper;
import com.creacionusuario.models.ResponseModel;
import com.creacionusuario.repository.UserRepository;
import com.creacionusuario.service.JwtService;
import com.creacionusuario.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UsuarioServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private PhoneMapper phoneMapper;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UsuarioService usuarioService;
    private User user;
    private List<User> userList;
    private RegistroUsuarioContract registroUsuarioContract;
    private ResponseRegistroContract responseRegistroContract;
    private ActualizacionUsuarioContract updateUser;
    @BeforeEach
    void setUp(){
        openMocks(this);
        user = TestObjectBuilder.getUser();
        userList = Collections.singletonList(user);
        registroUsuarioContract = TestObjectBuilder.getRegistroUsuarioContract();
        responseRegistroContract = TestObjectBuilder.getResponseRegistroContract();
        updateUser = TestObjectBuilder.getActualizacionUsuarioContract();
        when(userRepository.findAllByIsActive(true)).thenReturn(Optional.ofNullable(userList));
        when(usuarioMapper.mapToSaveUserRecord(registroUsuarioContract)).thenReturn(user);
        when(jwtService.generateToken(anyString())).thenReturn("aToken");
        when(userRepository.save(user)).thenReturn(user);
        when(usuarioMapper.mapToResponseRegistro(user)).thenReturn(responseRegistroContract);
        when(userRepository.findOneByIdAndIsActive(UUID.fromString("c17c1d11-726d-4b9a-a8c0-0a2e5cf4c032"), Boolean.TRUE)).thenReturn(Optional.ofNullable(user));
        when(usuarioMapper.mapToUpdateRecord(user, updateUser)).thenReturn(user);
    }

    @Test
    void shouldGetUserSuccessfull(){
        when(userRepository.findOneByEmail(anyString())).thenReturn(user);

        User result = userRepository.findOneByEmail("anyEmail");

        assertNotNull(result);
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getPassword(), result.getPassword());
        assertEquals(user.getCreated(), result.getCreated());
    }
    @Test
    void shouldGetAllUserSuccessfull(){

        Optional<List<User>> result = userRepository.findAllByIsActive(Boolean.TRUE);

        assertTrue(result.isPresent());
        assertEquals(user.getEmail(), result.get().get(0).getEmail());
        assertEquals(user.getName(), result.get().get(0).getName());
        assertEquals(user.getPassword(), result.get().get(0).getPassword());
        assertEquals(user.getCreated(), result.get().get(0).getCreated());
    }

    @Test
    void shouldSaveUser() throws EmailExisteException {
        when(userRepository.findOneByEmail(anyString())).thenReturn(null);

        ResponseEntity<ResponseRegistroContract> result = usuarioService.registrarUsuario(registroUsuarioContract);

        assertNotNull(result);
        assertEquals(responseRegistroContract.getToken(), Objects.requireNonNull(result.getBody()).getToken());
        assertEquals(responseRegistroContract.getId(), result.getBody().getId());
        assertEquals(responseRegistroContract.getCreated(), result.getBody().getCreated());
        assertEquals(responseRegistroContract.getModified(), result.getBody().getModified());
    }

    @Test
    void shouldUpdateUser() throws ApiException {
        ResponseModel responseModel = TestObjectBuilder.getSuccesfullResponse();
        updateUser.setId("c17c1d11-726d-4b9a-a8c0-0a2e5cf4c032");
        ResponseEntity<ResponseModel> result = usuarioService.modificarUsuario(updateUser);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(result.getBody()).getMensaje()).isEqualTo("Usuario actualizado correctamente.");
        assertThat(result.getBody().getCodigo()).isEqualTo(200);
    }

    @Test
    void shouldDeleteUser() throws ApiException {
        ResponseEntity<ResponseModel> result = usuarioService.deleteUsuario("c17c1d11-726d-4b9a-a8c0-0a2e5cf4c032");

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(result.getBody()).getMensaje()).isEqualTo("Usuario eliminado correctamente.");
        assertThat(result.getBody().getCodigo()).isEqualTo(200);
    }
}
