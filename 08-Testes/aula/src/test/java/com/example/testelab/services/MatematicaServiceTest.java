package com.example.testelab.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MatematicaServiceTest {

    @Test
    public void deveriaSomarDoisNumeros(){ // O nome do metodo teste deve descrever a ação do metodo testado

        /*  Cenario */
        MatematicaService matematicaService = new MatematicaService();

        /* Ação */
        int resultado = matematicaService.somar(1, 2);

        /* Verificação */

        // System.out.print(resultado); // Verificação visual(apenas mostrar valores)
        assertTrue(resultado == 5); // Verificação por Assert

    }

}
