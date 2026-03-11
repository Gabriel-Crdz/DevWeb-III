package com.rotas.pizzatopping.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ex1")
public class ProdutoController {

    @GetMapping("/produtos")
    @ResponseBody
    public String listarProdutos() {
        return "Listar todos os produtos";
    }

    @GetMapping("/produtos/{id}")
    @ResponseBody
    public String listarProduto(@PathVariable String id) {
        return "id do produto" + id;
    }
    
    @PostMapping("/produtos")
    @ResponseBody
    public String inserirProduto() {
        return "Um novo produto com os dados foi cadastrado";
    }
    
    @PutMapping("/produtos/{id}")
    @ResponseBody
    public String atualizarProduto(@PathVariable String id) {
        
        return "Um produto com o" + id + "foi atualizado";
    }
    
}
