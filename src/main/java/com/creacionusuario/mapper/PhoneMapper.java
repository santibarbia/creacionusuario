package com.creacionusuario.mapper;

import com.creacionusuario.entities.Phone;
import com.creacionusuario.entities.User;
import com.creacionusuario.models.PhoneModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
public class PhoneMapper {

    public PhoneModel mapToPhoneUser(Phone response){
        return PhoneModel.builder()
                .countryCode(String.valueOf(response.getCoutryCode()))
                .number(String.valueOf(response.getNumber()))
                .cityCode(String.valueOf(response.getCityCode()))
                .build();
    }
    public Phone mapToPhoneRecord(PhoneModel response, User record){
        return Phone.builder()
                .user(record)
                .coutryCode(Integer.valueOf(response.getCountryCode()))
                .cityCode(Integer.valueOf(response.getCityCode()))
                .number(Integer.valueOf(response.getNumber()))
                .build();
    }
}
