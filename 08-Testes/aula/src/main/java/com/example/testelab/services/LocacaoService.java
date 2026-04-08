package com.example.testelab.services;

import java.time.LocalDate;

import com.example.testelab.models.Filme;
import com.example.testelab.models.Locacao;
import com.example.testelab.models.Usuario;
import com.example.testelab.utils.DataUtils;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, Filme filme) throws Exception{

		if(filme.getEstoque() == 0) { // Melhor forma de tratar as regras de negocio
			throw new Exception("Filme sem estoque");
		}

		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(LocalDate.now());
		locacao.setValor(filme.getPrecoLocacao());

		// Entrega no dia seguinte
		LocalDate dataEntrega = DataUtils.adicionarDias(LocalDate.now(), 1);

		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// TODO adicionar método para salvar no banco de dados

		return locacao;
	}

	// public static void main(String[] args) {
	// 	LocacaoService service = new LocacaoService();

	// 	Usuario usuario = new Usuario("Vinicius Rambo");
	// 	Filme filme = new Filme("Meu Malvado Favorito 3", 4, 2.50);

	// 	Locacao locacao = service.alugarFilme(usuario, filme);

	// 	/* Testes de Verificação */
	// 	System.out.println(locacao.getValor() == 2.50);
	// 	System.out.println(locacao.getFilme().getEstoque() == 5);
	// 	System.out.println(locacao.getDataRetorno() == DataUtils.obterDataComDiferencaDias(1));
	// }

}