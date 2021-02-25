package com.valcir.testvia.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DisponibilidadeServicoNF implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="autorizador_id")
	private Autorizador autorizador;
	
	private Date momentoConsulta;
	
	private String disponibilidade;
	
	public DisponibilidadeServicoNF() {
		// TODO Auto-generated constructor stub
	}

	public DisponibilidadeServicoNF(Integer id, Autorizador autorizador, Date momentoConsulta, String disponibilidade) {
		super();
		this.id = id;
		this.autorizador = autorizador;
		this.momentoConsulta = momentoConsulta;
		this.disponibilidade = disponibilidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Autorizador getAutorizador() {
		return autorizador;
	}

	public void setAutorizador(Autorizador autorizador) {
		this.autorizador = autorizador;
	}

	public Date getMomentoConsulta() {
		return momentoConsulta;
	}

	public void setMomentoConsulta(Date momentoConsulta) {
		this.momentoConsulta = momentoConsulta;
	}

	public String getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DisponibilidadeServicoNF other = (DisponibilidadeServicoNF) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
