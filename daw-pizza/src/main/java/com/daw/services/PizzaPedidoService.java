package com.daw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.PizzaPedido;
import com.daw.persistence.repositories.PizzaPedidoRepository;
import com.daw.services.exceptions.PizzaPedidoException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PizzaPedidoService {

	private final PizzaPedidoRepository pizzaPedidoRepository;

	public List<PizzaPedido> findAll() {
		return this.pizzaPedidoRepository.findAll();
	}

	public PizzaPedido findById(long idPizzaPedido) {
		if (!this.pizzaPedidoRepository.existsById(idPizzaPedido)) {
			throw new PizzaPedidoException(String.format("La PizzaPedido con id %d no existe", idPizzaPedido));
		}
		return this.pizzaPedidoRepository.findById(idPizzaPedido).get();
	}

	public PizzaPedido create(PizzaPedido pizzaPedido) {
		pizzaPedido.setId(0L);
		return this.pizzaPedidoRepository.save(pizzaPedido);
	}

	public PizzaPedido update(long idPizzaPedido, PizzaPedido pizzaPedido) {
		PizzaPedido pizzaPedidoBD = this.findById(idPizzaPedido);
		pizzaPedidoBD.setIdPedido(pizzaPedido.getIdPedido());
		pizzaPedidoBD.setIdPizza(pizzaPedido.getIdPizza());
		pizzaPedidoBD.setCantidad(pizzaPedido.getCantidad());
		pizzaPedidoBD.setPrecio(pizzaPedido.getPrecio());
		return this.pizzaPedidoRepository.save(pizzaPedidoBD);
	}

	public void deleteById(long idPizzaPedido) {
		this.pizzaPedidoRepository.delete(this.findById(idPizzaPedido));
	}
}
