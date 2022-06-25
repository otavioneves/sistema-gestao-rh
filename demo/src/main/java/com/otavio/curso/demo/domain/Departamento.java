package com.otavio.curso.demo.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity			// anotação para transformar essa classe em uma entidade gerenciada pela jpa.
@Table(name="DEPARTAMENTOS")			// anotação para informar o nome da tabela no banco de dados
public class Departamento extends AbstractEntity<Long>{

	@NotBlank(message="Informe um nome.")		// anotação que valida se o nome está todo em branco
	@Size(min=3, max=60,message = "O nome do departamento deve ter entre {min} e {max} caracteres.")		// valida o tamanho do nome à ser colocado no departamento
	@Column(name = "nome", nullable = false, unique = true, length = 60)		// anotação para informar o nome da coluna do atributo abaixo. O atributo nullable settado como false informa que essa coluna será obrigatório. A anotação unique define que a coluna é única.A anotação length informa o tamanho.
	private String nome;

	@OneToMany(mappedBy = "departamento")
	private List<Cargo> cargos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
		
}
