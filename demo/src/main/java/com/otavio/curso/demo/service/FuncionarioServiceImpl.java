package com.otavio.curso.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otavio.curso.demo.dao.FuncionarioDao;
import com.otavio.curso.demo.domain.Funcionario;

@Transactional(readOnly=true)
@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	FuncionarioDao funcionarioDao;
	
	@Transactional(readOnly=false)
	@Override
	public void salvar(Funcionario funcionario) {
		funcionarioDao.save(funcionario);
	}

	@Transactional(readOnly=false)
	@Override
	public void editar(Funcionario funcionario) {
		funcionarioDao.update(funcionario);
	}

	@Transactional(readOnly = false)
	@Override
	public void excluir(Long id) {
		funcionarioDao.delete(id);
	}

	@Override
	public Funcionario buscarPorId(Long id) {
		
		return funcionarioDao.findById(id);
	}

	@Override
	public List<Funcionario> buscarTodos() {
		
		return funcionarioDao.findAll();
	}
	
	@Override
	public List<Funcionario> buscarPorNome(String nome) {
		
		return funcionarioDao.findByNome(nome);
	}
	
	@Override
	public List<Funcionario> buscarPorCargo(Long id) {
		
		return funcionarioDao.findByCargoId(id);
	}

	@Override
    public List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida) {
	    if (entrada != null && saida != null) {	    	
            return funcionarioDao.findByDataEntradaDataSaida(entrada, saida);
        } else if (entrada != null) {        	
	        return funcionarioDao.findByDataEntrada(entrada);
        } else if (saida != null) {        	
	        return funcionarioDao.findByDataSaida(saida);
        } else {
        	return new ArrayList<>();
        }
    }

}
