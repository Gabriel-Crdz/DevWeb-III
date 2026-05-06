package com.example.persistencia.controllers;

import com.example.persistencia.models.Professor;
import com.example.persistencia.repositories.ProfessorRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfessorController {
    
    @Autowired //  É utilizado para realizar a injeção de dependência automática
    ProfessorRepository repository;

    @GetMapping("/professores")
    public String getProfessores(
        Model model,
        @RequestParam(required = false, defaultValue = "1") Integer pagina, 
        @RequestParam(required = false, defaultValue = "") String nome
    ){       

    List<Professor> professores = repository.obterProfessores(5, pagina, nome);
             
    model.addAttribute("professores", professores);
    model.addAttribute("pagina", pagina); // valor do numero de pagina
    model.addAttribute("nome", nome); // nome usado no filtro de pesquisa

    return "professores";
    }
}
