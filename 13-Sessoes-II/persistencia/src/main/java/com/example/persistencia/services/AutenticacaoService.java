package com.example.persistencia.services;

import java.util.UUID;

import com.example.persistencia.repositories.ProfessorRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.persistencia.models.Professor;

@Service
public class AutenticacaoService {

    @Autowired
    private HttpServletRequest request; //  Cria uma requisição

    @Autowired
    private HttpServletResponse response; // Cria uma resposta

    @Autowired
    private HttpSession session; // Cria uma nova sessão

    @Autowired
    ProfessorRepository repository;
    
    public Professor autenticar(String email, String senha){
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

        return professor;
    }

    public void logout(){
        session.invalidate(); // Apaga a sessão existente

        Cookie coo = new Cookie("APP_SESSID", ""); // Substitui o cookie anteriormente criado

        coo.setPath("/");
        coo.setMaxAge(0); // Tempo de duração do cookie, usado para fazer o cookie expirar e se tornar invalido

        response.addCookie(coo);
    }

    public Professor getProfessorLogado(){
        try{
            Cookie[] cookies = request.getCookies();

            if(cookies == null){
                return null;
            }

            for(Cookie coo : cookies){
                if(coo.getName().equals("APP_SESSID")){ // Valida se a um cookie para APP_SESSID
                    String sessId = coo.getValue(); // Obtem o valor do cookie

                    Professor prof = (Professor) session.getAttribute(sessId); // Converte o retorno para um objeto Professor
                    return prof;
                }
            }
            return null; // Não encontrou o cookie correspondente
        }   
        catch(Exception e){
            return null; // não encontrou um professor
        }
    }
}
