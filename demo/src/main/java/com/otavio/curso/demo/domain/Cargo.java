package com.otavio.curso.demo.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long> {

	@NotBlank(message="O nome do cargo é obrigatório.")
	@Size(max=60, message = "O nome do cargo deve conter no máximo {max} caracteres.")
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;

	@NotNull(message="Selecione o departamento relativo ao cargo.")		// como essa seleção é um combobox, colocamos essa anotação para evitar ir null
	@ManyToOne // anotação para criar a chave estrangeira.
	@JoinColumn(name = "id_departamento_fk") // anotação para criar a coluna dessa chave estrangeira.
	private Departamento departamento;

	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionarios;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
