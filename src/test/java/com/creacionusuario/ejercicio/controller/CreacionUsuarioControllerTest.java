package com.creacionusuario.ejercicio.controller;

import com.creacionusuario.controller.CreacionUsuarioController;
import com.creacionusuario.controller.contract.ActualizacionUsuarioContract;
import com.creacionusuario.controller.contract.RegistroUsuarioContract;
import com.creacionusuario.controller.contract.ResponseRegistroContract;
import com.creacionusuario.controller.contract.ResponseUserContract;
import com.creacionusuario.ejercicio.utils.TestObjectBuilder;
import com.creacionusuario.exception.ApiException;
import com.creacionusuario.exception.EmailExisteException;
import com.creacionusuario.models.ResponseModel;
import com.creacionusuario.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreacionUsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private CreacionUsuarioController controller;

    private String email;
    private ResponseUserContract mockResponseUserContract;
    private List<ResponseUserContract> userContracts;
    private RegistroUsuarioContract registroUsuarioContract;
    private ResponseRegistroContract responseRegistroContract;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        email = "juan@perez.org";
        mockResponseUserContract =TestObjectBuilder.getResponseUserContract();
        userContracts = TestObjectBuilder.getListUserContract();
        registroUsuarioContract = TestObjectBuilder.getRegistroUsuarioContract();
        responseRegistroContract = TestObjectBuilder.getResponseRegistroContract();
    }

    @Test
    public void shouldReturnUserDetailsSuccessfull() throws ApiException {

        when(usuarioService.getUser(email)).
                thenReturn(new ResponseEntity<>(mockResponseUserContract, HttpStatus.OK));
        ResponseEntity<ResponseUserContract> response = controller.getUsers(email);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(mockResponseUserContract);
        verify(usuarioService, times(1)).getUser(email);
    }

    @Test
    void shouldReturnAllUserDetailsSuccessfull() throws ApiException {
        when(usuarioService.getAllUsers()).thenReturn(new ResponseEntity<>(userContracts, HttpStatus.OK));

        ResponseEntity<List<ResponseUserContract>> response = controller.getAllUsers();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(userContracts);
        verify(usuarioService, times(1)).getAllUsers();
    }

    @Test
    void shouldPostUserSuccessfull() throws ApiException {
        when(usuarioService.registrarUsuario(registroUsuarioContract)).thenReturn(new ResponseEntity<>(responseRegistroContract, HttpStatus.OK));

        ResponseEntity<ResponseRegistroContract> response = controller.postUsuario(registroUsuarioContract);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(responseRegistroContract);
        verify(usuarioService, times(1)).registrarUsuario(registroUsuarioContract);
    }

    @Test
    void shouldThrowEmailException() {
        registroUsuarioContract.setEmail("");

        ApiException response = assertThrows(ApiException.class,() -> controller.postUsuario(registroUsuarioContract));

        assertEquals("Formato de email no valido", response.getMessage());

    }

    @Test
    void shouldUpdateUserSuccessfull() throws Exception {
        ResponseModel model = TestObjectBuilder.getSuccesfullResponse();
        ActualizacionUsuarioContract contract = TestObjectBuilder.getActualizacionUsuarioContract();
        when(usuarioService.modificarUsuario(contract)).thenReturn(new ResponseEntity<>(model,HttpStatus.OK));

        ResponseEntity<ResponseModel> response = controller.updateUsuario(contract);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getMensaje()).isEqualTo("anyMessage");
        assertThat(response.getBody().getCodigo()).isEqualTo(200);
        verify(usuarioService, times(1)).modificarUsuario(contract);
    }

    @Test
    void shouldDeleteUserSuccessfull() throws ApiException {
        ResponseModel model = TestObjectBuilder.getSuccesfullResponse();
        when(usuarioService.deleteUsuario(anyString())).thenReturn(new ResponseEntity<>(model, HttpStatus.OK));

        ResponseEntity<ResponseModel> response = controller.deleteUsuario(anyString());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).getMensaje()).isEqualTo("anyMessage");
        assertThat(response.getBody().getCodigo()).isEqualTo(200);
        verify(usuarioService, times(1)).deleteUsuario(anyString());
    }
}
