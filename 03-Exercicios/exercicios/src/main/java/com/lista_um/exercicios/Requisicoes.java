package com.lista_um.exercicios;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/exercicio2")
public class Requisicoes {
    @GetMapping("/requisicao")
    @ResponseBody
    public String requisicao(HttpServletRequest request) {
        String metodo = request.getMethod();
        String URI = request.getRequestURI();
        String URL = request.getRequestURL().toString();
        String protocolo = request.getProtocol();
      
        String resultado = metodo + " - " + URI + " - " + URL + " - " + protocolo;
        return resultado;
    }
   
    @GetMapping("/cabecalhos")
    @ResponseBody
    public String cabecalhos(HttpServletRequest request) {
   	    String host = request.getHeader("host");
   	    String agent = request.getHeader("user-agent");
   	
   	    return host + " - " + agent;
    }
   
    @GetMapping("/saudacao")
    @ResponseBody
    public String saudacao(HttpServletRequest request){
   	    String nome = request.getParameter("nome");
            return "Olá, " + nome;
    }

    @GetMapping("/bebidas/{type}")
    @ResponseBody
    public String bebidas(@PathVariable String type){
        return "A categoria da bebida é: " + type;
    }
}
