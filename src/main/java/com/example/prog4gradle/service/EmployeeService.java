package com.example.prog4gradle.service;

import com.example.prog4gradle.modele.Employee;
import com.example.prog4gradle.repository.EmployeeRepository;
import com.example.prog4gradle.repository.entity.EmployeeEntity;
import com.example.prog4gradle.repository.mapper.EmployeeMapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository repository;
    private EmployeeMapper mapper;
    private TelephoneService telephoneService;


    public List<Employee> getEmployees() {
        List<Employee> employees = repository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toUnmodifiableList());
        return employees;
    }

    public void addEmployee(Employee employee) {
        EmployeeEntity employeeEntity = mapper.toEntity(employee);
        repository.save(employeeEntity);

    }

    public void updateEmployee(Long id, Employee employee) {
        EmployeeEntity entity = repository.getById(id);
        if (entity != null) {
            entity.setFirstName(employee.getFirstName());
            entity.setLastName(employee.getLastName());
            entity.setPassword(employee.getPassword());
            entity.setMatrix(employee.getMatrix());
            entity.setImage(employee.getImage());
            entity.setSex(employee.getSex());
            entity.setCountryCode(employee.getCountryCode());
            entity.setTelephone(employee.getTelephone());
            entity.setAdresse(employee.getAdresse());
            entity.setEmailPerso(employee.getEmailPerso());
            entity.setEmailPro(employee.getEmailPro());
            entity.setCin(employee.getCin());
            entity.setPost(employee.getPost());
            entity.setChild(employee.getChild());
            entity.setDepartDate(employee.getDepartDate());
            entity.setEmbaucheDate(employee.getEmbaucheDate());
            entity.setCategory(employee.getCategory());
            entity.setNumeroCnaps(employee.getNumeroCnaps());
            repository.save(entity);
        }
        ;
    }

    public EmployeeEntity getById(Long id) {
        return repository.getById(id);
    }

    public List<Employee> getByFinder(String finder) {
        return repository.findByFirstNameOrLastNameOrSexOrPostContainingIgnoreCase(finder).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toUnmodifiableList());
    }
    public List<Employee> getByDateFinder(String dateFinder){
        return repository.findByEmbaucheDate(dateFinder).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toUnmodifiableList());
    }

    public EmployeeEntity getByFirstName(String firstName){
       return repository.getByFirstName(firstName);

    }

}
