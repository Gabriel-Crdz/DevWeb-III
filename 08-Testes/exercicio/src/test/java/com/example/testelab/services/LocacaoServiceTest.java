package com.example.testelab.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.testelab.models.*;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


public class LocacaoServiceTest {

    @Test
    // @DisplayName() // Bibliotecas mais modernas possoe notação para descrever o metodo
    public void naoDeveAlugarFilme_QuandoEStoqueMenorQueZero(){

        /* Cenario */
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario();
        Filme filme = new Filme("Alice no pais das maravilhas(1975)", 0, 2.50);
        List<Filme> filmes  = new ArrayList<>();
        filmes.add(filme);

        Locacao locacao = null;
        /* Ação */
        try{
            
            locacao = service.alugarFilme(usuario, filmes);

            fail("Não pode alugar filme sem estoque");
        }
        catch(Exception e){
            /* Verificação */

            assertNotNull(locacao);

        }
    }

    @Test
    public void deveLancarUmaExcecao_QuandoEstoqueForZerado() throws Exception{
        /* Cenario */
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario();
        Filme filme = new Filme("Alice no pais das maravilhas(1975)", 0, 2.50);

        List<Filme> filmes  = new ArrayList<>();
        filmes.add(filme);

        assertThrows(Exception.class, () -> {
            service.alugarFilme(usuario, filmes);
        });

    }
}
