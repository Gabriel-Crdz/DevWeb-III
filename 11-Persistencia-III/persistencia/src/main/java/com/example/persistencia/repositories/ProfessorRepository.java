package com.example.persistencia.repositories;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.persistencia.exception.BancoDadosException;
import com.example.persistencia.infrastructure.ConexaoFactory;
import com.example.persistencia.models.*;;

@Repository // marca uma interface ou classe como um componente de acesso a dados na camada de persistência.
public class ProfessorRepository {

    private Connection conexao;

    public ProfessorRepository() {
        conexao = ConexaoFactory.obterConexao();
    }

    public List<Professor> obterProfessores(Integer limite, Integer pag, String nome) {
        
        List<Professor> professores = new ArrayList<>();
        PreparedStatement consulta = null;
        ResultSet resultados = null;

        Integer offset = (pag - 1) * limite; // Calculo para determinar o valor do offset(sql) para a paginação
        String comando = "SELECT p.*, c.nome AS curso_nome "
            + "FROM professores AS p "
            + "JOIN cursos as c ON p.curso_id = c.curso_id "
            + "WHERE p.nome LIKE ? "
            + "ORDER BY p.nome ASC "
            + "LIMIT ? "
            + "OFFSET ?;";

        try {
            consulta = conexao.prepareStatement(comando);
            
            consulta.setString(1, "%" + nome + "%");
            consulta.setInt(2, limite);
            consulta.setInt(3, offset);

            resultados = consulta.executeQuery();

            Map<Integer, Curso> cursosMap = new HashMap<>(); // Map para validar a quantidade de cursos instanciados

            while (resultados.next()) { // Itera a tabela de resultados

                /* Para cada linha lida um novo curso é criado(Forma Incorreta) */
                // Curso curso = new Curso(); 
                // curso.setId(resultados.getInt("curso_id"));
                // curso.setNome(resultados.getString("curso_nome"));
                
                /* Forma mais adequada de instanciar o curso (Objeto Monitorado)*/
                Curso curso = cursosMap.get(resultados.getInt("curso_id")); // Valida cada linha para só existir apenas um objeto curso para cada ID
                
                if(curso == null){ // Cria um novo curso apenas se ele nao existir dentro do Map
                    curso = new Curso();
                    curso.setId(resultados.getInt("curso_id"));
                    curso.setNome(resultados.getString("curso_nome"));

                    cursosMap.put(resultados.getInt("curso_id"), curso);
                }

                professores.add(resultadoProfessor(resultados, curso));
            }
            
        } catch (SQLException e) {
            throw new BancoDadosException(e.getMessage());
        }
        finally{ // Fechamento do ResultSet e Statement( Boa pratica, para não gerar acumulo de lixo na memoria) 
              
            ConexaoFactory.fecharStatement(consulta);
            ConexaoFactory.fecharResultSet(resultados);
        }

        return professores;
    }

    public Professor resultadoProfessor(ResultSet resultados, Curso curso) throws SQLException{

        Professor p = new Professor();
        p.setId(resultados.getInt("professor_id"));
        p.setNome(resultados.getString("nome"));
        p.setEmail(resultados.getString("email"));
        p.setDataNascimento(resultados.getDate("data_nascimento").toLocalDate()); // toLocalDate = converte o Date(banco de dados) para o LocalDate(java)
        p.setSalarioBase(resultados.getDouble("salario_base"));
        
        p.setCurso(curso);

        return p;
    }

    public Professor findProfessorById(Integer cod) {
        PreparedStatement consulta = null; // Statement precompilado
        ResultSet resultados = null;
        Professor professor = null;

        try {

            /* Tratamento do SQL */
            String comando = "SELECT p.*, c.nome AS curso_nome"
            + " FROM professores p JOIN cursos as c ON p.curso_id = c.curso_id WHERE p.professor_id = ? ;";
            consulta = conexao.prepareStatement(comando); // Consulta preparada

            consulta.setInt(1, cod); // Passando o valor do parametro para o comando sql (segurança contra SQL Injection)

            resultados = consulta.executeQuery(); // Executando a consulta

            /* ORM */
            while (resultados.next()) { 
                Curso curso = new Curso();
                curso.setId(resultados.getInt("curso_id"));
                curso.setNome(resultados.getString("curso_nome"));

                professor = resultadoProfessor(resultados, curso);
            }
            
        } catch (SQLException e) {
            throw new BancoDadosException(e.getMessage());
        }
        finally{ // Fechamento do ResultSet e Statement( Boa pratica, para não gerar acumulo de lixo na memoria) 
              
            ConexaoFactory.fecharStatement(consulta);
            ConexaoFactory.fecharResultSet(resultados);
        }

        return professor;
    }

    public Professor inserir(Professor professor){
        PreparedStatement consulta = null;
        
        try{
            String comando = "INSERT INTO professores (nome, email, data_nascimento, salario_base, curso_id) " +
            "VALUES(?, ?, ?, ?, ?)";

            consulta = conexao.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS); // Executa a inserção e retorna o ID gerado


            consulta.setString(1, professor.getNome());
            consulta.setString(2, professor.getEmail());
            consulta.setDate(3, Date.valueOf(professor.getDataNascimento())); // Date.valueOf: Converte LocalDate(java) para Date(sql)
            consulta.setDouble(4, professor.getSalarioBase());
            consulta.setInt(5, professor.getCurso().getId());

            int linhasAfetadas = consulta.executeUpdate();

            if(linhasAfetadas > 0){
                System.out.println("SUCESSO: " + linhasAfetadas + "linhas afetadas!");
                ResultSet ids = consulta.getGeneratedKeys();

                ids.next(); // Pula a linha do cabeçalho

                int id = ids.getInt(1); // Pega os dados da primeira coluna
            
                professor.setId(id);
            }
            else{
                System.out.println("AVISO: " + linhasAfetadas + "inserções");
            }
        }
        catch( SQLException e){
            throw new BancoDadosException("ERRO ao inserir: " + e.getMessage());
        }
        finally{
            ConexaoFactory.fecharStatement(consulta);
        }

        return professor;
    }
}