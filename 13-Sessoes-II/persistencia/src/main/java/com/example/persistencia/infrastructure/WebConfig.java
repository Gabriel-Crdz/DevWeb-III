package com.example.persistencia.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Autowired
    AutenticacaoInterceptor autenticacaoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) { // Registra os interceptadores
        registry.addInterceptor(autenticacaoInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/login", "/logar", "/logout"); // executa para todas as rotas exceto as de login propriamente
    }
}
