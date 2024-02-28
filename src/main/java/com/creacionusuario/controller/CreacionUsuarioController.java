package com.creacionusuario.controller;

import com.creacionusuario.controller.contract.ActualizacionUsuarioContract;
import com.creacionusuario.controller.contract.RegistroUsuarioContract;
import com.creacionusuario.controller.contract.ResponseRegistroContract;
import com.creacionusuario.controller.contract.ResponseUserContract;
import com.creacionusuario.entities.User;
import com.creacionusuario.exception.ApiException;
import com.creacionusuario.models.ResponseModel;
import com.creacionusuario.service.UsuarioService;
import com.creacionusuario.utils.ValidEmailPatternUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Usuario Rest")
@RequiredArgsConstructor
@Slf4j
public class CreacionUsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping(value = "/usuario/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Obtener usuario por email")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
    public ResponseEntity<ResponseUserContract> getUsers(@PathVariable(name = "email") String email) throws ApiException {

        log.info("Comenzando validacion email: {}", email);
        ValidEmailPatternUtils.emailPatterValid(email);
        log.info("Validacion correcta");
        return usuarioService.getUser(email);

    }

    @GetMapping(value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Obtener todos los usuario")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
    public ResponseEntity<List<ResponseUserContract>> getAllUsers() throws ApiException {

        return usuarioService.getAllUsers();

    }


    @PostMapping(value = "/usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Registrar usuario")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RegistroUsuarioContract.class))))
    public ResponseEntity<ResponseRegistroContract> postUsuario(@RequestBody RegistroUsuarioContract body) throws ApiException {
        log.info("validando campo de email");
        ValidEmailPatternUtils.emailPatterValid(body.getEmail());
        log.info("Validacion correcta");
        return usuarioService.registrarUsuario(body);
    }

    @PutMapping(value = "/usuario/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Modificar usuario")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ActualizacionUsuarioContract.class))))
    public ResponseEntity<ResponseModel> updateUsuario(@RequestBody ActualizacionUsuarioContract body) throws ApiException {
        log.info("validando campo de email");
        ValidEmailPatternUtils.emailPatterValid(body.getEmail());
        log.info("Validacion correcta");
        return usuarioService.modificarUsuario(body);
    }
    @DeleteMapping(value = "/usuario/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Borrar usuario")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ActualizacionUsuarioContract.class))))
    public ResponseEntity<ResponseModel> deleteUsuario(@PathVariable(name = "id") String id) throws ApiException {

        return usuarioService.deleteUsuario(id);
    }

}
