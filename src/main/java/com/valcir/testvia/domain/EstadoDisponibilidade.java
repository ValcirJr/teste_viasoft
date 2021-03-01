package com.valcir.testvia.domain;

import java.io.Serializable;

public class EstadoDisponibilidade implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	private Estado estado;
	private Autorizador autorizador;
	private DisponibilidadeServicoNF disponibilidade;
	
	public EstadoDisponibilidade() {
		// TODO Auto-generated constructor stub
	}

	public EstadoDisponibilidade(Estado estado, Autorizador autorizador, DisponibilidadeServicoNF disponibilidade) {
		super();
		this.estado = estado;
		this.autorizador = autorizador;
		this.disponibilidade = disponibilidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Autorizador getAutorizador() {
		return autorizador;
	}

	public void setAutorizador(Autorizador autorizador) {
		this.autorizador = autorizador;
	}

	public DisponibilidadeServicoNF getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(DisponibilidadeServicoNF disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
	@Override
	public String toString() {
		return estado.getNome() + autorizador.getNome() + disponibilidade.getDisponibilidade();
	}
	
}
