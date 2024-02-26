package com.creacionusuario.controller.contract;

import com.creacionusuario.models.PhoneModel;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegistroUsuarioContract {

    private String name;
    private String email;
    private String password;
    private List<PhoneModel> phones;


}
