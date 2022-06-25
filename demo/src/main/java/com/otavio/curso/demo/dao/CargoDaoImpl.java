package com.otavio.curso.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.otavio.curso.demo.domain.Cargo;
import com.otavio.curso.demo.util.PaginacaoUtil;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {

	public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao){
		
		int tamanho = 5; 			// tamanho máximo de resultados
		int inicio = (pagina - 1)*tamanho;		// para pegar o primeiro registro, por exemplo: 0*5=0 (0,1,2,3,). 1*5=5 (5,6,7,8,9)
		
		List<Cargo> cargos = getEntityManager().createQuery("select c from Cargo c order by c.nome " + direcao, Cargo.class).setFirstResult(inicio).setMaxResults(tamanho).getResultList();
		
		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		return new PaginacaoUtil<Cargo>(tamanho, pagina, totalDePaginas, direcao, cargos);
		
		
	}
	
	public long count() {
		return getEntityManager().createQuery("select count(*) from Cargo",Long.class).getSingleResult();
	}
	
}
