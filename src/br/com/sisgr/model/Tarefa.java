package br.com.sisgr.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "tarefa")
public class Tarefa {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String descricao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	private Date prazo;
	
	private boolean status = false;

	@ManyToOne()
	@JoinColumn(name = "reuniaoId")
	private Reuniao reuniao;
	
	@ManyToOne()
	@JoinColumn(name = "contatoId")
	private Contato contato;
	
	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Reuniao getReuniao() {
		return reuniao;
	}

	public void setReuniao(Reuniao reuniao) {
		this.reuniao = reuniao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}	
}
