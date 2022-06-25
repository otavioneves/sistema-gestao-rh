package com.otavio.curso.demo.dao;

import java.util.List;

import com.otavio.curso.demo.domain.Cargo;
import com.otavio.curso.demo.util.PaginacaoUtil;

public interface CargoDao {

	void save(Cargo cargo);
	
	void update(Cargo cargo);
	
	void delete (Long id);
	
	Cargo findById(Long id);
	
	List<Cargo> findAll();
	
	PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao);
}
