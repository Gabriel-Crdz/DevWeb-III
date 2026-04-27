package com.lista_tres.gerenciador_tarefas.enums;

public enum EstadoTarefa { // ENUM para estados das tarefas
    
    EM_ANDAMENTO("Em andamento"), 
    FEITO("Feito"), // Valores do ENUM
    CANCELADO("Cancelado");
    
    private String descricao;

    EstadoTarefa(String descricao){ // Construtor
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }
}
