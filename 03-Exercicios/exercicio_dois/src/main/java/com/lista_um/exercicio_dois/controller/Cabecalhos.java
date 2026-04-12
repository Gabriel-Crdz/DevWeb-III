package com.lista_um.exercicio_dois.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class Cabecalhos {

    @ResponseBody
    @GetMapping("/cabecalhos")
    public String cabecalhos(HttpServletRequest request) {
   	    String host = request.getHeader("host");
   	    String agent = request.getHeader("user-agent");
        String encoding = request.getCharacterEncoding();
        String language = request.getLocale().getLanguage();
   	
   	    return host + "<br>" + agent + "<br>" + encoding + "<br>" + language;
    }
}
