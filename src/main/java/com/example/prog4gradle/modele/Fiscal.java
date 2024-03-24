package com.example.prog4gradle.modele;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class Fiscal {
    private Long id;
    private String NIF ;
    private String stat;
    private String rcs ;
}
