package com.example.prog4gradle.modele;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class Entreprise {
   private Long id ;
   private String name;
   private String description;
   private String Address;
   private String email;
   private String phone;
   private Fiscal fiscal;
   private String logo ;


}
