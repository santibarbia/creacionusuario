package com.creacionusuario.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseModel {

    private Integer codigo;
    private String mensaje;
}
