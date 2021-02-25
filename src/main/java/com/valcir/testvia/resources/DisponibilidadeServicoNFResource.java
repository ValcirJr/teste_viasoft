package com.valcir.testvia.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.valcir.testvia.services.ConsultaDisponibilidadeService;
import com.valcir.testvia.services.exception.ObjectNotFoundException;

@RestController
@RequestMapping("/disponibilidade")
public class DisponibilidadeServicoNFResource {

	
	@Autowired
	private ConsultaDisponibilidadeService service;
	
	@RequestMapping(value="/{estado}", method=RequestMethod.GET)
	public ResponseEntity<?> listarPorEstado(@PathVariable String estado) throws ObjectNotFoundException {
		
		return ResponseEntity.ok().body(service.atualPorEstado(estado));
	}
}
