package com.apresentacao.thymeleaf.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping({"", "/"})
public class ThymeleafController {

    @GetMapping
    public String home(Model model) {

        ArrayList<String> lista = new ArrayList<>();
        lista.add("Vinicius");
        lista.add("Breno");
        lista.add("Guilherme");
        lista.add("Amanda");
        lista.add("Jefferson");

        // Busca os produtos no banco de dados

        // Gerar lista
        String turma = "TADS";
        Integer ano = 2026;

        /* Passagem de atributos para a resposta  */
        model.addAttribute("turma", turma);
        model.addAttribute("ano", ano);
        model.addAttribute("lista", lista);
        return "home";
    }
    
}
