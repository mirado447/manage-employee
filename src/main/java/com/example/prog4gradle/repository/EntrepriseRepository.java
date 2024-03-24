package com.example.prog4gradle.repository;

import com.example.prog4gradle.repository.entity.EmployeeEntity;
import com.example.prog4gradle.repository.entity.EntrepriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<EntrepriseEntity,Long> {

}
