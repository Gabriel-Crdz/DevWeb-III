package com.lista_tres.gerenciador_tarefas.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;

import com.lista_tres.gerenciador_tarefas.enums.EstadoTarefa;
import com.lista_tres.gerenciador_tarefas.models.Tarefa;
import com.lista_tres.gerenciador_tarefas.util.IdSequencia;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {
    private List<Tarefa> listaTarefas;

    public TarefaController() {
        listaTarefas = new ArrayList<>();

        Tarefa t1 = new Tarefa(IdSequencia.proximoID(), "Passeio", "Levar a tartaruga para passear", LocalDate.now());
        Tarefa t2 = new Tarefa(IdSequencia.proximoID(), "Consulta", "Ir a consulta do dentista", LocalDate.now());
        Tarefa t3 = new Tarefa(IdSequencia.proximoID(), "Compras", "Fazer uma lista de compras", LocalDate.now());

        listaTarefas.add(t1);
        listaTarefas.add(t2);
        listaTarefas.add(t3);
    }

    @GetMapping({ "", "/", "listar" })
    public String listarTarefas(Model model) {
        model.addAttribute("tarefas", listaTarefas); // permite adicionar objetos a uma lista do tipo “chave - valor”
        model.addAttribute("estados", EstadoTarefa.values());
        return "tarefa-lista";
    }

    @GetMapping("/cadastrar")
    public String cadastrarTarefa() {
        return "tarefa-cadastro";
    }

    @PostMapping("/salvar")
    public String cadastrarTarefa(Tarefa tarefa) {
        tarefa.setId(IdSequencia.proximoID()); // Define o ID para a tarefa
        this.listaTarefas.add(tarefa);
        return "redirect:/tarefas";
    }

    @GetMapping("/info/{id}")
    public String informarTarefa(@PathVariable long id, Model model) {

        for (Tarefa t : listaTarefas) {
            if (t.getId().equals(id)) {
                model.addAttribute("tarefa", t); // Adiciona a tarefa ao modelo
                return "tarefa-info";
            }
        }
        return "redirect:/tarefas";
    }

    @GetMapping("/editar/{id}")
    public String editarTarefa(@PathVariable long id, Model model) {
        for (Tarefa t : listaTarefas) {
            if (t.getId().equals(id)) {
                model.addAttribute("tarefa", t);
                model.addAttribute("estados", EstadoTarefa.values());
                return "tarefa-editar";
            }
        }
        return "redirect:/tarefas";
    }

    @PostMapping("/atualizar")
    public String atualizarTarefa(Tarefa tarefa){
        for (Tarefa t : listaTarefas) {
            if (t.getId().equals(tarefa.getId())) {
                t.setTitulo(tarefa.getTitulo());
                t.setDescricao(tarefa.getDescricao());
                t.setEstado(tarefa.getEstado());
                t.setData(tarefa.getData());
                break;
            }
        }
        return "redirect:/tarefas"; 
    }

     @GetMapping("/remover/{id}")
    public String removerTarefa(@PathVariable long id) {
        for (Tarefa t : this.listaTarefas) {
            if (t.getId().equals(id)) {
                listaTarefas.remove(t); // Remove a tarefa da lista
                break;
            }
        }
        return "redirect:/tarefas";
    }

    @GetMapping("/andamento")
    public String filtrarTarefas(Model model) {
        List<Tarefa> tarefasFiltradas = new ArrayList<>();

        for (Tarefa t : listaTarefas) {
            if (t.getEstado() == EstadoTarefa.EM_ANDAMENTO) {
                tarefasFiltradas.add(t);
            }
        }
        model.addAttribute("tarefas", tarefasFiltradas);

        return "tarefa-lista";
    }
    
   
}
