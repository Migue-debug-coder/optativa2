package com.daw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pedido;
import com.daw.persistence.repositories.PedidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {
	private final PedidoRepository pedidoRepository;
	
	public List<Pedido> findAll(){
		return this.pedidoRepository.findAll();
	}

}
