package com.example.testelab;

import org.junit.jupiter.api.Test;

import com.example.testelab.models.Usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/* Usando a Classe Assert */
public class AssertTest {

    @Test
    public void deveMostrarAsserçõesComJunit(){
        /* Alguns exemplos */

        assertTrue(true); // Verifica se é TRUE
        assertFalse(false); // Verifica se é FALSE

        assertNull(null); // Verifica se é NULO
        assertNotNull(new Object()); // Verifica se não é NULO

        assertEquals(1, 1); // Verifica se dois valores são IGUAIS (o 1º é o valor recebido, o 2º é o valor de comparação esperada)
        assertEquals("casa", "carlos", "Erro de Comparação"); // E possivel mostraer uma mensagem caso nao sejam iguais

        assertEquals(0.12345, 0.12, 0.01); // Delta de Precisão

        Usuario u1 = new Usuario("Carlos");

        assertSame(u1, u1); // Verifica se um objeto é ele mesmo
    }

}
