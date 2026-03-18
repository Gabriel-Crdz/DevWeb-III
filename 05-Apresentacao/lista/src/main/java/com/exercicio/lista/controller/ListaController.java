package com.exercicio.lista.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping({"", "/"})
public class ListaController {

    @GetMapping
    public String todo(Model model) {

        ArrayList<String> lista = new ArrayList<>();

        lista.add("tomar banho");
        lista.add("fazer cafe");
        lista.add("tomar cafe");

        model.addAttribute("lista", lista);
        return "todo";
    }

    @PostMapping("/add")
    public String add() {
        System.out.println("metodo chamado");
        return "";
    }
    
    
}
