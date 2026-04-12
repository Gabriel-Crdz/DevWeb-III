package com.lista_um.exercicio_dois.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class Requisicao {

    @ResponseBody
    @GetMapping("/requisicao")
    public String requisicao(HttpServletRequest request) {
    String metodo = request.getMethod();
    String URL = request.getRequestURL().toString();
    String protocolo = request.getProtocol();
            
    String resultado = metodo + "<br>" + URL + "<br>" + protocolo;
    return resultado;
    }
}