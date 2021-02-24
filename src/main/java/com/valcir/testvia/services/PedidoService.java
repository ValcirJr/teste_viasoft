package com.valcir.testvia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valcir.testvia.domain.Pedido;
import com.valcir.testvia.repositories.PedidoRepository;

import com.valcir.testvia.services.exception.ObjectNotFoundException;



@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	
	public Pedido buscar(Integer id) throws ObjectNotFoundException {

		Optional<Pedido> obj= repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!, id: " + id
				+ ", Tipo: " + Pedido.class.getName()));
		
	}
	
}
