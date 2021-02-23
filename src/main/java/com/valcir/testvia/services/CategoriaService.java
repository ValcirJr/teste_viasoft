package com.valcir.testvia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valcir.testvia.domain.Categoria;
import com.valcir.testvia.repositories.CategoriaRepository;

import com.valcir.testvia.services.exception.ObjectNotFoundException;



@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	
	public Categoria buscar(Integer id) throws ObjectNotFoundException {

		Optional<Categoria> obj= repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!, id: " + id
				+ ", Tipo: " + Categoria.class.getName()));
		
	}
	
}
