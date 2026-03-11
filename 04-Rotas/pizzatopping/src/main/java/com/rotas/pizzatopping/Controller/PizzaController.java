package com.rotas.pizzatopping.Controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping({"", "/"})
public class PizzaController {
    private ArrayList<String> menuPizzas = new ArrayList<>();

    public PizzaController(){
        menuPizzas.add("Calabresa");
        menuPizzas.add("Catupiry");
        menuPizzas.add("Portuguesa");
        menuPizzas.add("Quatro Queijos");
        menuPizzas.add("Strogonoff");
    }

    @GetMapping("/pizzas")
    @ResponseBody
    public String pizzas(){
        return menuPizzas.toString();
    }

    @GetMapping("/pizzas/{tipo}")
    @ResponseBody
    public String pizzaTipo(@PathVariable String tipo, @RequestParam(required = false) String precoMax){

        // if(tipo.equals("vegetariana")) return "Não temos essa opção";
        // else if (tipo.equals("especial")) return menuPizzas.get(0);
        // return menuPizzas.toString();

        if(precoMax == null) return "O preço maximo não foi informado";

        return "O preco maximo selecionado foi " + precoMax;
    }

    @PostMapping("/cadastrar")
    @ResponseBody
    public String cadastrarPizza(@RequestParam String pizza){
        this.menuPizzas.add(pizza);
        return "pizza cadastrada";
    }
    
}
