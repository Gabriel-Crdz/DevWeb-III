package com.example.persistencia.repositories;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.persistencia.exception.BancoDadosException;
import com.example.persistencia.infrastructure.ConexaoFactory;
import com.example.persistencia.models.*;;

public class ProfessorRepository {

    private Connection conexao;

    public ProfessorRepository() {
        conexao = ConexaoFactory.obterConexao();
    }

    public List<Professor> obterProfessores() {
        
         List<Professor> professores = new ArrayList<>();
        Statement consulta = null;
        ResultSet resultados = null;

        try {
            consulta = conexao.createStatement();
            resultados = consulta.executeQuery("SELECT * FROM professores");

            while (resultados.next()) { // Iterar a tabela de resultados
                professores.add(resultadoProfessor(resultados));
            }
            
        } catch (SQLException e) {
            throw new BancoDadosException(e.getMessage());
        }
        
        return professores;
    }

    public Professor resultadoProfessor(ResultSet resultados) throws SQLException{

                Professor p = new Professor();
                p.setId(resultados.getInt("professor_id"));
                p.setNome(resultados.getString("nome"));
                p.setEmail(resultados.getString("email"));
                p.setDataNascimento(resultados.getDate("data_nascimento").toLocalDate()); // toLocalDate = converte o Date(banco de dados) para o java
                p.setSalarioBase(resultados.getDouble("salario_base"));

                return p;
            }
}