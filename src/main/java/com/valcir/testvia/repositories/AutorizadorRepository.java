package com.valcir.testvia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valcir.testvia.domain.Autorizador;

@Repository
public interface AutorizadorRepository extends JpaRepository<Autorizador, Integer>{
	
}
