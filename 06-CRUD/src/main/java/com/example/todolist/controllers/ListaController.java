package com.example.lista.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.processing.Generated;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todolist.enums.EstadoTarefa;
import com.example.todolist.models.Tarefa;

@Controller
public class ListaController {

    public ListaController(){ // Construtor
        /* Inserções de testes */
        Tarefa t1 = new Tarefa("Ler um bom livro", new LocalDate.now());

        Tarefa t2 = new Tarefa("Levar o gato pra caçar", new LocalDate.now());

        Tarefa t3 = new Tarefa("Marcar dentista", new LocalDate.now());

        lista.add(t1);
        lista.add(t2);
        lista.add(t3);
    }

    private List<Tarefa> lista = new ArrayList<>(); // Declaração da lista 

    @GetMapping({"", "/", "/tarefas" })
    public String todos(Model model){

        model.addAttribute("lista", lista);

        return "lista";

    }

    @PostMapping("/add") // Rota para adicionar um nova tarefa
    public String add(Tarefa tarefa){

        this.lista.add(tarefa);

        return "redirect:/tarefas"; // Apos adicionar redireciona para "tarefas"

    }

    @GetMapping("/apagar/{id}") // Rota para deletar uma tarefa
    public String remover(@PathVariable UUID id){

        for(Tarefa tarefa : this.lista){
            if(tarefa.getId().equals(id)){
                lista.remove(tarefa); // Remove a tarefa da lista
                break;
            }
        }

        return "redirect:/tarefas"; // Apos remover redireciona para "tarefas"

    }

    @GetMapping("/alterar/{id}") // Rota para alterar o estado de uma tarefa
    public String alterarEstado(@PathVariable UUID id){
        for(Tarefa tarefa : this.lista){
            if(tarefa.getId().equals(id)){
                tarefa.setEstado(tarefa.getEstado().equals(EstadoTarefa.EM_ANDAMENTO) ? EstadoTarefa.FEITO : EstadoTarefa.EM_ANDAMENTO); // Altera o estado da tarefa
            }
        }

        return "redirect:/tarefas"; // Apos alterar redireciona para "tarefas"
    }

    @GetMapping("/editar/{id")
    public String editar(@PathVariable UUID id, Model model){

        for(Tarefa tarefa : this.lista){
            if(tarefa.getId().equals(id)){
               model.addAttribute("tarefa", tarefa); // Adiciona a tarefa ao modelo
               return "tarefa-editar";
            }
        }

        return "redirect:/tarefas";
    }
}
