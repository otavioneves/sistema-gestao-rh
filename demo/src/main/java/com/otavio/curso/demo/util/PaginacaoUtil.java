package com.otavio.curso.demo.util;

import java.util.List;

public class PaginacaoUtil<T> {

	private int tamanho;		// quantidade de registros
	private int pagina;			// número da página atual
	private long totalDePaginas;		// quantidade total de páginas
	private String direcao;
	private List<T> registros;		// recebe o resultado da consulta do banco de dados para exibir na tabela, usando o for each.

	
	
	public PaginacaoUtil(int tamanho, int pagina, long totalDePaginas, String direcao, List<T> registros) {
		super();
		this.tamanho = tamanho;
		this.pagina = pagina;
		this.totalDePaginas = totalDePaginas;
		this.direcao = direcao;
		this.registros = registros;
	}

	
	
	public String getDirecao() {
		return direcao;
	}

	public int getTamanho() {
		return tamanho;
	}
	public int getPagina() {
		return pagina;
	}
	public long getTotalDePaginas() {
		return totalDePaginas;
	}
	public List<T> getRegistros() {
		return registros;
	}
	
}
