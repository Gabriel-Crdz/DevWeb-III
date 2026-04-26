package com.lista_um.exercicio_dois.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Saudacoes {

    @GetMapping("/saudacao")
    @ResponseBody
    public String saudacao(HttpServletRequest request){
        String nome = request.getParameter("nome");
        
        return "Olá, " + nome;  
    }
    
    @GetMapping("/bebidas/{type}")
    @ResponseBody
    public String bebidas(@PathVariable String type){
        return "A categoria da bebida é: " + type;
    }
}
