package com.example.prog4gradle.repository.mapper;

import com.example.prog4gradle.modele.Employee;
import com.example.prog4gradle.repository.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@AllArgsConstructor
public class EmployeeMapper {

    public Employee toDomain(EmployeeEntity entity){
        return Employee.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .password(entity.getPassword())
                .birthdate(entity.getBirthDate())
                .matrix(entity.getMatrix())
                .image(entity.getImage())
                .sex(entity.getSex())
                .countryCode(entity.getCountryCode())
                .telephone(entity.getTelephone())
                .adresse(entity.getAdresse())
                .emailPerso(entity.getEmailPerso())
                .emailPro(entity.getEmailPro())
                .cin(entity.getCin())
                .post(entity.getPost())
                .child(entity.getChild())
                .departDate(entity.getDepartDate())
                .embaucheDate(entity.getEmbaucheDate())
                .category(entity.getCategory())
                .numeroCnaps(entity.getNumeroCnaps())
                .build();
    }
    public EmployeeEntity toEntity(Employee employee){
        return EmployeeEntity.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .password(employee.getPassword())
                .birthDate(employee.getBirthdate())
                .matrix(employee.getMatrix())
                .image(employee.getImage())
                .sex(employee.getSex())
                .countryCode(employee.getCountryCode())
                .telephone(employee.getTelephone())
                .adresse(employee.getAdresse())
                .emailPerso(employee.getEmailPerso())
                .emailPro(employee.getEmailPro())
                .cin(employee.getCin())
                .post(employee.getPost())
                .child(employee.getChild())
                .departDate(employee.getDepartDate())
                .embaucheDate(employee.getEmbaucheDate())
                .category(employee.getCategory())
                .numeroCnaps(employee.getNumeroCnaps())
                .build();
    }


}
