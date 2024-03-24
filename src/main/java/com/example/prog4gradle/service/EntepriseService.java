package com.example.prog4gradle.service;

import com.example.prog4gradle.modele.Entreprise;
import com.example.prog4gradle.repository.EntrepriseRepository;
import com.example.prog4gradle.repository.mapper.EntrepriseMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EntepriseService {
    private EntrepriseMapper entrepriseMapper;
    private EntrepriseRepository entrepriseRepository;

    public Entreprise getById(Long id ){
        Entreprise entreprise = entrepriseMapper.toDomain(entrepriseRepository.getById(id));
        return entreprise;
    }

}
