package br.com.sisgr.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "usuario")
public class Usuario extends Pessoa{

	@NotNull(message = "{usuario.cadastro.senha.obrigatorio}")
	@Size(min = 3, max = 10, message = "{usuario.cadastro.senha.tamanho}")
	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
