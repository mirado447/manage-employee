package com.example.prog4gradle.repository.mapper;

import com.example.prog4gradle.modele.Entreprise;
import com.example.prog4gradle.repository.entity.EntrepriseEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class EntrepriseMapper {
    public Entreprise toDomain(EntrepriseEntity entity){
          return Entreprise.builder()
                  .name(entity.getName())
                  .build();
     }

}
