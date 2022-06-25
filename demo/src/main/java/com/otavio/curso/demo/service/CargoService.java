package com.otavio.curso.demo.service;

import java.util.List;

import com.otavio.curso.demo.domain.Cargo;
import com.otavio.curso.demo.util.PaginacaoUtil;

public interface CargoService {

	void salvar(Cargo cargo);
	
	void editar(Cargo cargo);
	
	void excluir(Long id);
	
	Cargo buscarPorId(Long id);
	
	List<Cargo> buscarTodos();
	
	boolean cargoTemFuncionarios(Long id);
	
	PaginacaoUtil<Cargo> buscaPorPagina(int pagina, String direcao);
}
