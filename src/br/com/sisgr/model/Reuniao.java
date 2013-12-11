package br.com.sisgr.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "reuniao")
public class Reuniao {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer id;
	
	private String nome;
	
	private String descricao;
	
	private String local;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	private Date dia;
	
	@DateTimeFormat(pattern = "HH:mm")  
	private Date inicio;
	
	@DateTimeFormat(pattern = "HH:mm")  
	private Date fim;
	
	@ManyToOne
	@JoinColumn(name = "usuarioId")
	private Usuario usuario;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "reuniaoContato",joinColumns = { @JoinColumn(name = "reuniaoId") }, 
	inverseJoinColumns = { @JoinColumn(name = "contatoId") })
	private List<Contato> contatos;
	
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@Fetch(FetchMode.SUBSELECT)
//	@JoinTable(name = "reuniaoTarefa",joinColumns = { @JoinColumn(name = "reuniaoId") }, 
//	inverseJoinColumns = { @JoinColumn(name = "tarefaId") })
//	private List<Tarefa> tarefas;
//	
//	public List<Tarefa> getTarefas() {
//		return tarefas;
//	}
//
//	public void setTarefas(List<Tarefa> tarefas) {
//		this.tarefas = tarefas;
//	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

}
