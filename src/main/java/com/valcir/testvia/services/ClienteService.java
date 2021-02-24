package com.valcir.testvia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valcir.testvia.domain.Cliente;
import com.valcir.testvia.repositories.ClienteRepository;

import com.valcir.testvia.services.exception.ObjectNotFoundException;



@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	
	public Cliente buscar(Integer id) throws ObjectNotFoundException {

		Optional<Cliente> obj= repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!, id: " + id
				+ ", Tipo: " + Cliente.class.getName()));
		
	}
	
}
