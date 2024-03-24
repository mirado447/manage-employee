package com.example.prog4gradle.repository.mapper;

import com.example.prog4gradle.modele.Telephone;
import com.example.prog4gradle.repository.entity.TelephoneEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TelephoneMapper {
    public String toDomain(TelephoneEntity entity){
        return Telephone.builder()
                .numberPhone(entity.getNumberPhone())
                .build().toString();
    }
    public TelephoneEntity toEntity(Telephone telephone){
        return TelephoneEntity.builder()
                .countryCode(telephone.getCountryCode())
                .numberPhone(telephone.getNumberPhone())
                .build();
    }
}
