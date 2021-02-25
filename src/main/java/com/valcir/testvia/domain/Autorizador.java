package com.valcir.testvia.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Autorizador implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name= "ESTADO_AUTORIZADOR",
			joinColumns = @JoinColumn(name="autorizador_id"),
			inverseJoinColumns = @JoinColumn(name= "estado_id"))
	private List<Estado> estados = new ArrayList<>();
		
	@JsonIgnore
	@OneToMany(mappedBy="autorizador")
	private List<DisponibilidadeServicoNF> disponiblidadeServicos = new ArrayList<>();

	public Autorizador() {
		// TODO Auto-generated constructor stub
	}
	public Autorizador(Integer id, String nome, List<Estado> estados,
			List<DisponibilidadeServicoNF> disponiblidadeServicos) {
		super();
		this.id = id;
		this.nome = nome;
		this.estados = estados;
		this.disponiblidadeServicos = disponiblidadeServicos;
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
	public List<Estado> getEstados() {
		return estados;
	}
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	public List<DisponibilidadeServicoNF> getDisponiblidadeServicos() {
		return disponiblidadeServicos;
	}
	public void setDisponiblidadeServicos(List<DisponibilidadeServicoNF> disponiblidadeServicos) {
		this.disponiblidadeServicos = disponiblidadeServicos;
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
		Autorizador other = (Autorizador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	} 
		
}
