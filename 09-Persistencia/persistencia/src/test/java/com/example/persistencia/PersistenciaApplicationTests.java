package com.example.persistencia;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.persistencia.infrastructure.ConexaoFactory;
import com.example.persistencia.models.Professor;
import com.example.persistencia.repositories.ProfessorRepository;

@SpringBootTest
class PersistenciaApplicationTests {

	@Test
	void databaseTest() {
		ConexaoFactory.obterConexao(); // Testando a conexão
	}

	@Test
	void deveObterUmaListaDeProfessores(){
		ProfessorRepository repository = new ProfessorRepository();

		List<Professor> professores = repository.obterProfessores();

		for(Professor p : professores){
			System.out.println(p.toString());
		}
	}

}
