package com.example.todolist.enums;

public enum EstadoTarefa { // ENUM para estados das tarefas
    
    EM_ANDAMENTO("Em andamento"), 
    FEITO("Feito"); // Valores do ENUM

    private String descricao;

    EstadoTarefa(String descricao){ // Construtor
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }
}
