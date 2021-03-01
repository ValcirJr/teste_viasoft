package com.valcir.testvia.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.valcir.testvia.domain.Estado;
import com.valcir.testvia.services.EstadoService;
import com.valcir.testvia.services.exception.ObjectNotFoundException;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<?> listar() throws ObjectNotFoundException {
		List<Estado> obj = service.buscarEstados();
		return ResponseEntity.ok().body(obj);
	}
	
}
