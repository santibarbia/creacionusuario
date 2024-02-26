package com.creacionusuario.controller.contract;

import com.creacionusuario.models.PhoneModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseActualizacionUsuarioContract {
    private String id;
    private String name;
    private String email;
    private String password;
    private List<PhoneModel> phones;
}
