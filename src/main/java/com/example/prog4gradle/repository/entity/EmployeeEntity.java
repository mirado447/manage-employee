package com.example.prog4gradle.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Base64;
import java.util.Date;

@Entity(name = "employee")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String password;
    private String birthDate;
    @Column(name = "matricule")
    private String matrix;
    @Column(columnDefinition = "text")
    private String image ;
    private String sex ;
    private String countryCode;
    private String telephone;
    private String adresse ;
    private String emailPro ;
    private String emailPerso;
    private String cin;
    private String post;
    private Integer child;
    private String embaucheDate ;
    private String departDate;
    private String category;
    private String numeroCnaps;

};
