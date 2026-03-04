package com.lista_um.exercicios;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"", "/", "/exercicio1"})
public class HelloWorld {
    @GetMapping
    @ResponseBody
    public String hello() {
      return "Olá, Mundo!";
   }
}
