package com.valcir.testvia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.valcir.testvia.domain.DisponibilidadeServicoNF;

@Repository
public interface DisponibilidadeServicoNFRepository extends JpaRepository<DisponibilidadeServicoNF, Integer>{

//	List<DisponibilidadeServicoNF> findByStartDateAfterAndStartDateBefore(Date dataInical, Date dataFinal);
	
	@Query(value = "SELECT dispserv.DISPONIBILIDADE, est.NOME, dispserv.MOMENTO_CONSULTA\r\n"
			+ " FROM DISPONIBILIDADE_SERVICONF AS dispserv\r\n"
			+ "INNER JOIN ESTADO AS est \r\n"
			+ "ON est.SIGLA = :sigla \r\n"
			+ "INNER JOIN ESTADO_AUTORIZADOR AS estaut\r\n"
			+ "ON estaut.ESTADO_ID = est.ID\r\n"
			+ "WHERE dispserv.MOMENTO_CONSULTA <= :datafinal AND dispserv.MOMENTO_CONSULTA >= :datainicial AND estaut.AUTORIZADOR_ID = dispserv.AUTORIZADOR_ID", 
			  nativeQuery = true)
	List<DisponibilidadeServicoNF> findDisponibilidadesEntreDatasPorEstado
				( @Param("datainicial")String datainicial, @Param("datafinal")String datafinal, @Param("sigla")String sigla);

	@Query(value = "SELECT dispserv.DISPONIBILIDADE, est.NOME, dispserv.MOMENTO_CONSULTA\r\n"
			+ " FROM DISPONIBILIDADE_SERVICONF AS dispserv\r\n"
			+ "INNER JOIN ESTADO AS est \r\n"
			+ "WHERE dispserv.MOMENTO_CONSULTA >= :datainicial AND dispserv.MOMENTO_CONSULTA <= DATEADD(DAY, +1, :datafinal) \r\n"
			+ "ORDER BY est.NOME", nativeQuery = true)
	
	List<String> findDisponibilidadesEntreDatas (@Param("datainicial")String datainicial, @Param("datafinal")String datafinal);

	

}
