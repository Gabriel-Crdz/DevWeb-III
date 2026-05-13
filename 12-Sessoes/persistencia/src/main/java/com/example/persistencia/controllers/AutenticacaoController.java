package com.example.persistencia.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import com.example.persistencia.models.Professor;
import com.example.persistencia.repositories.ProfessorRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AutenticacaoController {

    @Autowired
    private HttpSession session; // Cria uma nova sessão

    @Autowired
    private HttpServletRequest request; // Cria uma requisição


    @Autowired
    private HttpServletResponse response; // Cria uma resposta

    @Autowired
    ProfessorRepository repository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @PostMapping("/logar")
    public String logar(String email, String senha){
        
        Professor professor = repository.findProfessorByEmail(email);
        
        if(professor == null){
            throw new RuntimeException("email invalido!");
        }
        
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        if(!encoder.matches(senha, professor.getSenha())){  // Compara a senha informada com a do banco de dados, se não for igual lança uma exceção
            throw new RuntimeException("senha incorreta");
        }
        
        String sessaoId = UUID.randomUUID().toString(); // Cria uma chave, identificador para a sessao

        session.setAttribute(sessaoId, professor); // Cada professor tera uma identificador unico, diferente do ID do banco de dados

        Cookie coo = new Cookie("APP_SESSID", sessaoId); // registra a chave
        coo.setPath("/"); // Valido para toda a aplicação
        coo.setHttpOnly(true); // Inacessivel para JS

        response.addCookie(coo); // Adiciona o cookie no header da resposta

        return "redirect:/professores";
    }
}
