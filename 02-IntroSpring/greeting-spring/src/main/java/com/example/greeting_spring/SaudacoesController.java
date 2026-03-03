package com.example.greeting_spring;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller // Permite acessar pela URL
// @RestController * COntroller + ResponseBody usado em APIs
@RequestMapping({"/", "/saudacao"}) // Permite mapeamento de rotas URL
public class SaudacoesController {

    @GetMapping // Mapeia o metodo GET
    @ResponseBody // A Resposta é mandada para o corpo da pagina
    public String saudacao(){
        return "Ola, Spring";
    }

    @GetMapping("/data") // Mapeia o metodo GET dentro da Rota "saudacao"
    @ResponseBody // A Resposta é mandada para o corpo da pagina
    public String data(){
        return LocalDateTime.now().toString(); // Mostra a Hora Atual
    }

    @GetMapping("/headers")
    @ResponseBody
    /* HttpServletRequest: Pega todas as informaçoes da requisição */
    public String headers(HttpServletRequest request){

        // String header = request.getHeader("user-agent");

        String info = request.getParameter("info"); // Captura os parametros do GET

        // return "Cabeçalho: " + header; // Mostra o Usuario(navegador)
        return "Informação: " + info;   
    }
}
