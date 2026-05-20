package com.example.persistencia.controllers;

import com.example.persistencia.models.Curso;
import com.example.persistencia.models.Professor;
import com.example.persistencia.repositories.ProfessorRepository;
import com.example.persistencia.services.AutenticacaoService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; 

@Controller
@RequestMapping("/professores") // Agora todas as operações respondem a rota "/professores" no geral
public class ProfessorController {

    @Autowired //  É utilizado para realizar a injeção de dependência automática
    ProfessorRepository repository;

    @Autowired
    AutenticacaoService autenticacao;

    @GetMapping("")
    public String getProfessores(
        Model model,
        @RequestParam(required = false, defaultValue = "1") Integer pagina, 
        @RequestParam(required = false, defaultValue = "") String nome
    ){       

    List<Professor> professores = repository.obterProfessores(5, pagina, nome);
             
    model.addAttribute("professores", professores);
    model.addAttribute("pagina", pagina); // valor do numero de pagina
    model.addAttribute("nome", nome); // nome usado no filtro de pesquisa

    return "professores";
    }

    @GetMapping("/criar")
    public String criarProfessor(Model model, Professor professor) {
        if(autenticacao.getProfessorLogado() == null) {return "redirect:/login";} // Valida se a um login valido, forma 

        model.addAttribute("professor", professor);
        model.addAttribute("cursos", this.getCursos());

        return "professores-criar";
    }
    
    private List<Curso> getCursos(){
        List<Curso> cursos = new ArrayList<>();

        cursos.add(new Curso(1, "Processos de Mesas"));
        cursos.add(new Curso(2, "Analise de Pinguins"));
        cursos.add(new Curso(3, "Engenharia de Alimentos"));

        return cursos;
    }

    @PostMapping("/salvar")
    public String postMethodName(Professor professor, Integer cursoId) {
        // for(Curso curso : this.getCursos()){
        //     if(curso.getId() == cursoId) {professor.setCurso(curso);}
        // }

        Curso cursoSelecionado = getCursos().stream().filter(c -> c.getId().equals(cursoId)) // Filtra para encontrar o curso selecionado pelo ID
        .findFirst() // Localiza o primeiro elemento
        .orElse(new Curso(cursoId, "Curso Novo")); // Caso não exista cria um novo curso

        professor.setCurso(cursoSelecionado);
        repository.inserir(professor);

        return "redirect:/professores";
    }
}