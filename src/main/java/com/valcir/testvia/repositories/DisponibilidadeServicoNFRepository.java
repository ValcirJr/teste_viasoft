package com.valcir.testvia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valcir.testvia.domain.DisponibilidadeServicoNF;

@Repository
public interface DisponibilidadeServicoNFRepository extends JpaRepository<DisponibilidadeServicoNF, Integer>{
	
}
