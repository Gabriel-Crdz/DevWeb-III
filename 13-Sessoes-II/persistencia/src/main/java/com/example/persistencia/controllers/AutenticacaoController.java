package com.example.persistencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.persistencia.services.AutenticacaoService;

@Controller
public class AutenticacaoController {

    @Autowired
    AutenticacaoService autenticacaoService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logar")
    public String logar(String email, String senha, Model model) {
        
        try{
            autenticacaoService.autenticar(email, senha); // Tenta validar o usuario
            return "redirect:/professores";

        }
        catch(RuntimeException e){

            model.addAttribute("erro", e.getMessage()); // Caso não consiga, passa os erros para a tela de login
            return "login";
        }
    }
    

    @GetMapping("/logout")
    public String logout(){
        
        autenticacaoService.logout();
        return "redirect:/login";
    }

}
