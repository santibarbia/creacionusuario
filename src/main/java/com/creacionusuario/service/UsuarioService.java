package com.creacionusuario.service;

import com.creacionusuario.constants.Constants;
import com.creacionusuario.controller.contract.ActualizacionUsuarioContract;
import com.creacionusuario.controller.contract.RegistroUsuarioContract;
import com.creacionusuario.controller.contract.ResponseRegistroContract;
import com.creacionusuario.controller.contract.ResponseUserContract;
import com.creacionusuario.entities.Phone;
import com.creacionusuario.entities.User;
import com.creacionusuario.exception.ApiException;
import com.creacionusuario.exception.EmailExisteException;
import com.creacionusuario.mapper.PhoneMapper;
import com.creacionusuario.mapper.UsuarioMapper;
import com.creacionusuario.models.PhoneModel;
import com.creacionusuario.models.ResponseModel;
import com.creacionusuario.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {

    private final UserRepository userRepository;
    private final UsuarioMapper usuarioMapper;
    private final PhoneMapper phoneMapper;
    private final JwtService jwtService;

    public ResponseEntity<ResponseUserContract> getUser(String email) throws ApiException {

        User user = userRepository.findOneByEmail(email);
        if (user == null) {
            throw new ApiException(Constants.NO_RESULT);
        }


        List<PhoneModel> phoneModels = user.getPhones().stream().map(
                phoneMapper::mapToPhoneUser
        ).collect(Collectors.toList());
        ResponseUserContract userContract = usuarioMapper.mapOneUserToResponse(user, phoneModels);
        return new ResponseEntity<>(userContract, HttpStatus.OK);
    }

    public ResponseEntity<List<ResponseUserContract>> getAllUsers() throws ApiException {
        List<User> userList = userRepository.findAllByIsActive(true).orElseThrow(() -> new ApiException(Constants.NO_RESULT));
        List<ResponseUserContract> userContract = userList.stream()
                .map(response -> {
                    List<PhoneModel> phoneModels = null;
                    if (response.getPhones() != null && !response.getPhones().isEmpty()) {
                        phoneModels = response.getPhones().stream()
                                .map(phoneMapper::mapToPhoneUser).collect(Collectors.toList());
                    }
                    return usuarioMapper.mapOneUserToResponse(response, phoneModels);
                }).collect(Collectors.toList());
        return new ResponseEntity<>(userContract, HttpStatus.OK);
    }

    public ResponseEntity<ResponseRegistroContract> registrarUsuario(RegistroUsuarioContract body) throws EmailExisteException {
        log.info("Comienza registro");
        User exist = userRepository.findOneByEmail(body.getEmail());
        if (exist != null){
            throw new EmailExisteException("El correo ya fue registrado");
        }
        User record = usuarioMapper.mapToSaveUserRecord(body);

        List<Phone> phoneList = body.getPhones().stream().map(
                response -> phoneMapper.mapToPhoneRecord(response, record)
        ).collect(Collectors.toList());
        String token = jwtService.generateToken(body.getName());

        record.setPhones(phoneList);
        record.setToken(token);
        User user = userRepository.save(record);

        log.info("Registro completado");
        ResponseRegistroContract result = usuarioMapper.mapToResponseRegistro(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> modificarUsuario(ActualizacionUsuarioContract body) throws ApiException {
        log.info("Comienza actualizacion");
        String uuid = StringUtils.hasText(body.getId()) ? body.getId() : "";

        User user = userRepository.findOneByIdAndIsActive(UUID.fromString(uuid), Boolean.TRUE).orElseThrow(() -> new ApiException("No se encontro el usuario en la base de datos"));

        User finalUser = usuarioMapper.mapToUpdateRecord(user, body);

        if (body.getPhones() != null && !body.getPhones().isEmpty()) {
            List<Phone> phoneList = body.getPhones().stream().map(
                    response -> phoneMapper.mapToPhoneRecord(response, finalUser)
            ).collect(Collectors.toList());
            finalUser.getPhones().addAll(phoneList);
        }

        userRepository.save(finalUser);
        log.info("Se actualizo correctamente");
        return new ResponseEntity<>(ResponseModel.builder().mensaje("Usuario actualizado correctamente.").codigo(HttpStatus.OK.value()).build(), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> deleteUsuario(String id) throws ApiException {

        String uuid = StringUtils.hasText(id) ? id : "";
        User user = userRepository.findOneByIdAndIsActive(UUID.fromString(uuid), Boolean.TRUE).orElseThrow(() -> new ApiException("No se encontro el usuario en la base de datos"));

        user.setIsActive(false);
        userRepository.save(user);

        return new ResponseEntity<>(ResponseModel.builder().mensaje("Usuario eliminado correctamente.").codigo(HttpStatus.OK.value()).build(), HttpStatus.OK);
    }

}
