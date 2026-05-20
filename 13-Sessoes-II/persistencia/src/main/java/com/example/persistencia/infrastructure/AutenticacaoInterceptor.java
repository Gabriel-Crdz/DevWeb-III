package com.example.persistencia.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AutenticacaoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String caminho = request.getRequestURI(); // Pega o caminho de acesso ao controller requisitado

        if (caminho.equals("/login") || caminho.equals("/logar") || caminho.equals("/logout") || caminho.equals("/professores")) { 
            return true; // Para essas rotas o usuario pode acessar sem login
        }

        if (request.getSession().getAttribute("usuarioLogado") == null) { 
            response.sendRedirect("/login"); // Se não tiver uma sessao, envia o usuario para a tela de login
            return false;
        }

        return true;
    }
}
