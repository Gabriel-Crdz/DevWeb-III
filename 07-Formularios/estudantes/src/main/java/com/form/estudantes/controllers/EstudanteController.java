package com.form.estudantes.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

import com.form.estudantes.models.Estudante;
import com.form.estudantes.services.FileUploadService;

@Controller
@RequestMapping("/estudantes")
public class EstudanteController {

    @Autowired // Instancia a classe automaticamente(Spring)
    FileUploadService fileUploadService;

    @GetMapping("/cadastrar")
    public String cadastrar(Estudante estudante){
        return "estudantes-cadastrar.html";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Estudante estudante, BindingResult results) throws IOException{

        if(results.hasErrors()){
            return "estudantes-cadastrar.html";
        }

        System.out.println("Nome: " + estudante.getNome());
        System.out.println("Data de Ingresso: " + estudante.getDataIngresso());
        System.out.println("Habilidades:");
        System.out.println(estudante.getAvatar().getOriginalFilename());

        for(String hab : estudante.getHabilidades()){
            System.out.println(hab + " ");
        }
        
        String nomeArquivo = fileUploadService.upload(estudante.getAvatar());
        System.out.println("Novo Arquivo é:" + nomeArquivo);

        return "";
    }
}
