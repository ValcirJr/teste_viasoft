package com.valcir.testvia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valcir.testvia.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
	
	List<Estado> findBySigla(String sigla); 
	List<Estado> findByNome(String nome);
	List<Estado> findAll();
	
}
