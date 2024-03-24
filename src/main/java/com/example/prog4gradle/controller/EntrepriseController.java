package com.example.prog4gradle.controller;

import com.example.prog4gradle.modele.Entreprise;
import com.example.prog4gradle.service.EntepriseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class EntrepriseController {
    private EntepriseService service;
    @GetMapping("/entreprise/{id}")
    public String getEntreprise(Long id , Model model){
        Entreprise entreprise = service.getById(id);
        model.addAttribute("entreprise",entreprise);
        return "headerFragment";
    }

}
