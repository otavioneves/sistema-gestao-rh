package com.otavio.curso.demo.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass		// informa para a JPA que esse será uma superclasse para as outras classes.
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {		// implementa já a Serializable, levando isso para todas as classes filhas dessa classe AbstractEntity

	// ao definir que a classe é abstrata, as classes filhas que quiserem utilizar essa classe só poderão utilizá-la como herança, e não por instância
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)		//anotação para marcar que esse atributo abaixo é um ID. A segunda anotação é para gerenciar a estratégia de geração automática desse ID
	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity<?> other = (AbstractEntity) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "id=" + id;
	}
	
}
