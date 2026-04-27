package com.lista_tres.gerenciador_tarefas.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.lista_tres.gerenciador_tarefas.enums.EstadoTarefa;

public class Tarefa {
    private Long id;
    private String titulo;
    private String descricao;
    private EstadoTarefa estado;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate data;

    public Tarefa(){}

    public Tarefa(Long id, String titulo, String descricao, LocalDate data){
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.estado = EstadoTarefa.EM_ANDAMENTO;
        this.data = data;
    }

    /*Getters, Setters */

    /* ID */
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    /* Titulo */
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /* Descrição */
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /* EstadoTarefa */
    public EstadoTarefa getEstado() {
        return estado;
    }   
    public void setEstado(EstadoTarefa estado) {
        this.estado = estado;
    }

    /* Data */
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

}
