package com.form.estudantes.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Component
public class Estudante {

    @NotBlank(message = "O campo nome não pode ser vazio!") 
    private String nome;

    @NotNull // O campo não pode conter valor nulo
    @PastOrPresent(message = "A data não pode ser uma data Futura")
    private LocalDate dataIngresso;

    @NotEmpty(message = "Selecione ao menos uma habilidade")
    private List<String> habilidades = new ArrayList<>(); // Caso o usuario nao informe nada, a lista e criada mesmo assim

    private MultipartFile avatar; // Classe para envio de arquivos no Spring

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataIngresso() {
        return dataIngresso;
    }
    public void setDataIngresso(LocalDate dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }
    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }
    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    
}