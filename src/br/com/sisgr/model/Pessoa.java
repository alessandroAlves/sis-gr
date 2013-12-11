package br.com.sisgr.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@MappedSuperclass
public class Pessoa {

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	@NotEmpty(message = "{usuario.cadastro.nome.obrigatorio}")
	String nome;
	
	String sobrenome;
	
	@NotNull
	@NotEmpty(message = "{usuario.cadastro.email.obrigatorio}")
	@Column(unique=false)
	String email;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
