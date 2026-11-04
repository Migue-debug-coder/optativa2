package com.daw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.PizzaPedido;
import com.daw.persistence.repositories.PizzaPedidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PizzaPedidoService {
private final PizzaPedidoRepository pizzaPedidoRepository;
	
	public List<PizzaPedido> findAll(){
		return this.pizzaPedidoRepository.findAll();
	}
}
