package com.creacionusuario.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PhoneModel {
    private String number;
    private String cityCode;
    private String countryCode;
}
