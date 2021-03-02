package com.valcir.testvia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valcir.testvia.domain.Estado;
import com.valcir.testvia.repositories.EstadoRepository;
import com.valcir.testvia.services.exception.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;
	
	
	public Estado buscar(Integer id) throws ObjectNotFoundException {

		Optional<Estado> obj= repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!, id: " + id
				+ ", Tipo: " + Estado.class.getName()));
		
	}
	
	public List<Estado> buscarEstados(){
		return repo.findAll();
	}
	
}
