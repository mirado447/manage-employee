package com.example.prog4gradle.modele;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class Employee {
    private Long id ;
    private String firstName;
    private String lastName ;
    private String password;
    private String birthdate ;
    private String matrix ;
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

}
