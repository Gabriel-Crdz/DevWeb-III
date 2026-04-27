package com.lista_tres.gerenciador_tarefas.util;

public class IdSequencia {
    private static long contador = 0;

    public static long proximoID(){
        contador += 1;
        return contador;
    }
}