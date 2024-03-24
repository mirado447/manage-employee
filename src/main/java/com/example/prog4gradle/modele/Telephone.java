package com.example.prog4gradle.modele;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class Telephone {
    private Long id ;
    private String countryCode;
    private String numberPhone ;
}
