package com.creacionusuario.mapper;

import com.creacionusuario.controller.contract.ActualizacionUsuarioContract;
import com.creacionusuario.controller.contract.RegistroUsuarioContract;
import com.creacionusuario.controller.contract.ResponseRegistroContract;
import com.creacionusuario.controller.contract.ResponseUserContract;
import com.creacionusuario.entities.User;
import com.creacionusuario.models.PhoneModel;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;


@Data
@Service
public class UsuarioMapper {

    public User mapToSaveUserRecord(RegistroUsuarioContract body){
        return User.builder()
                .isActive(true)
                .email(body.getEmail())
                .name(body.getName())
                .password(body.getPassword())
                .build();
    }
    public User mapToUpdateRecord(User user, ActualizacionUsuarioContract body){
        user.setEmail(body.getEmail());
        user.setName(body.getName());
        user.setPassword(body.getPassword());
        return user;
    }

    public ResponseUserContract mapOneUserToResponse(User user, List<PhoneModel> phoneModels){
        return ResponseUserContract.builder()
                .phones(phoneModels)
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
    public ResponseRegistroContract mapToResponseRegistro(User user){
        return ResponseRegistroContract.builder()
                .id(user.getId())
                .token(user.getToken())
                .created(user.getCreated())
                .isActive(user.getIsActive())
                .lastLogin(user.getLastLogin())
                .modified(user.getModified())
                .build();
    }
}
