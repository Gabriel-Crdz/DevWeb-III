package com.example.persistencia.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.example.persistencia.exception.BancoDadosException;

public class ConexaoFactory {
    
    private static Connection conexao = null; // Constante 

    public static Connection obterConexao() {
        String database = "jdbc:mysql://localhost/ifpr_cursos";
        String user = "root";
        String pass = "bancodedados";

        /* Padrão de projeto Singleton */
        /* garante a existência de apenas uma única instância de uma classe em toda a aplicação */
        if(conexao == null){
            try{ // o driver tenta estabelecer uma conexão
                return DriverManager.getConnection(database, user, pass); // Caso consiga retorna a conexão
            }
            catch(SQLException e){ // Tratamento da exceção SQL
                // System.out.println("USUARIO SEU BURRO!!!");
                throw new BancoDadosException(e.getMessage()); // Exceção não checada
            }
        }
        else{
            return conexao;
        }
    }

    public static void encerrarConexao(Connection conexão){
        
    }
}
