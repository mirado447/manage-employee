package com.example.prog4gradle.service;

import com.example.prog4gradle.repository.TelephoneRepository;
import com.example.prog4gradle.repository.entity.TelephoneEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TelephoneService {
    private TelephoneRepository repository ;

    public TelephoneEntity getByIdEmployee(Long id){
        return repository.getTelephoneEntityByIdEmployee(id);
    }



}
