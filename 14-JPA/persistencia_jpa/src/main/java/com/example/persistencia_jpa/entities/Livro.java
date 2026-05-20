package com.example.persistencia_jpa.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// @Getter // Cria os metodos Getter
// @Setter // Cria os metodos Setter

@Entity // Informa ao Spring para gerencair uma tabela no banco
@Data // Insere os metodos Getters e Setters
public class Livro implements Serializable {
    @Id // Informa que esse atributo será usado como id no banco
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Informa que o ID devera ser incremental
    private Long id;
    
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponivel = true;
    

}
