package com.valcir.testvia.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.valcir.testvia.domain.Cliente;
import com.valcir.testvia.services.ClienteService;
import com.valcir.testvia.services.exception.ObjectNotFoundException;


@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable Integer id) throws ObjectNotFoundException {
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
