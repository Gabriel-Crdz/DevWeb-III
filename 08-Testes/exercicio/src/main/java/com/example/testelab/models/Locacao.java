package com.example.testelab.models;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Locacao {

	private Usuario usuario;
	private	List<Filme> filmes = new ArrayList<Filme>();
	private LocalDate dataLocacao;
	private LocalDate dataRetorno;
	private Double valor;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(LocalDate dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public LocalDate getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(LocalDate dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(List<Filme> filmes) {
		for(Filme filme : filmes){
			this.valor += filme.getPrecoLocacao();
		}
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	
}