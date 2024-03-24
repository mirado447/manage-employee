package com.example.prog4gradle.repository.entity;

import com.example.prog4gradle.modele.Fiscal;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Entreprise")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EntrepriseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name;
    private String description;
    private String Address;
    private String email;
    private String phone;
    @OneToOne
    @JoinColumn(name = "id_fiscal")
    private FiscalEntity fiscal;
    private byte[] logo ;

}
