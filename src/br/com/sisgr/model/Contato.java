package br.com.sisgr.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "contato")
public class Contato extends Pessoa{
	
	@ManyToOne
	@JoinColumn(name = "usuarioId")
	private Usuario usuario;

	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name = "contatoId")
	private List<Tarefa> tarefas;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
}