package com.creacionusuario.ejercicio.utils;

import com.creacionusuario.controller.contract.RegistroUsuarioContract;
import com.creacionusuario.controller.contract.ActualizacionUsuarioContract;
import com.creacionusuario.controller.contract.ResponseRegistroContract;
import com.creacionusuario.controller.contract.ResponseUserContract;
import com.creacionusuario.entities.Phone;
import com.creacionusuario.entities.User;
import com.creacionusuario.models.PhoneModel;
import com.creacionusuario.models.ResponseModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class TestObjectBuilder {

    public static List<ResponseUserContract> getListUserContract() {
        List<ResponseUserContract> userContractList = new ArrayList<>();
        ResponseUserContract responseUserContract = getResponseUserContract();
        userContractList.add(responseUserContract);
        return userContractList;

    }

    public static RegistroUsuarioContract getRegistroUsuarioContract() {
        return RegistroUsuarioContract.builder()
                .phones(new ArrayList<>())
                .name("juan")
                .password("password123")
                .email("juan@perez.org")
                .build();
    }

    public static ResponseUserContract getResponseUserContract() {
        return ResponseUserContract.builder()
                .phones(new ArrayList<>())
                .name("juan")
                .password("password123")
                .email("juan@perez.org")
                .build();
    }

    public static ResponseRegistroContract getResponseRegistroContract(){
        return ResponseRegistroContract.builder()
                .modified(new Date(System.currentTimeMillis()))
                .lastLogin(new Date(System.currentTimeMillis()))
                .created(new Date(System.currentTimeMillis()))
                .token("tokenValid")
                .id(UUID.randomUUID())
                .isActive(Boolean.TRUE)
                .build();
    }

    public static ResponseModel getBadRequestResponse(){
        return ResponseModel.builder()
                .mensaje("Formato de email no valido")
                .codigo(409).build();
    }
    public static ResponseModel getSuccesfullResponse(){
        return ResponseModel.builder()
                .mensaje("anyMessage")
                .codigo(200).build();
    }
    public static ActualizacionUsuarioContract getActualizacionUsuarioContract(){
        return ActualizacionUsuarioContract.builder()
                .phones(new ArrayList<>())
                .name("juan")
                .password("password123")
                .email("juan@perez.org")
                .id("UUID")
                .build();
    }
    public static Phone getPhone(){
        return Phone.builder()
                .user(new User())
                .number(12345)
                .cityCode(57)
                .coutryCode(12)
                .build();
    }

    public static PhoneModel getPhoneModel(){
        return PhoneModel.builder()
                .number("12345")
                .cityCode("57")
                .countryCode("12")
                .build();
    }
    public static User getUser(){
        return User.builder()
                .modified(new java.sql.Date(2023,12,12))
                .lastLogin(new java.sql.Date(2023,12,12))
                .created(new java.sql.Date(2023,12,12))
                .token("tokenValid")
                .id(UUID.fromString("c17c1d11-726d-4b9a-a8c0-0a2e5cf4c032"))
                .isActive(Boolean.TRUE)
                .build();
    }
}
