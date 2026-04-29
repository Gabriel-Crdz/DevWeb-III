package com.example.persistencia;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.persistencia.infrastructure.ConexaoFactory;
import com.example.persistencia.models.Professor;
import com.example.persistencia.models.Curso;
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

	@Test
	void deveObterUmProfessorPeloId(){
		ProfessorRepository repository = new ProfessorRepository();

		Professor professor = repository.findProfessorById(5);

		System.out.print(professor);
	}

	@Test
	void deveInserirUmProfessor(){
		ProfessorRepository repository = new ProfessorRepository();

		Professor professor = new Professor();
		Curso curso = new Curso();
		curso.setId(1);

		professor.setNome("Vinicius Rambo");
		professor.setEmail("vncs.rambo@gmail.com");
		professor.setDataNascimento(LocalDate.of(2007, 01, 15));
		professor.setSalarioBase(5000.00);
		professor.setCurso(curso);
		
		professor = repository.inserir(professor);

		System.out.print(professor);
	}

}
