package com.daw.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pedido;
import com.daw.persistence.repositories.PedidoRepository;
import com.daw.services.exceptions.PedidoException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

	private final PedidoRepository pedidoRepository;

	public List<Pedido> findAll() {
		return this.pedidoRepository.findAll();
	}

	public Pedido findById(long idPedido) {
		if (!this.pedidoRepository.existsById(idPedido)) {
			throw new PedidoException(String.format("El Pedido con id %d no existe", idPedido));
		}
		return this.pedidoRepository.findById(idPedido).get();
	}

	public Pedido create(Pedido pedido) {
		pedido.setId(0L);
		pedido.setFecha(LocalDateTime.now());
		pedido.setTotal(0.0);
		return this.pedidoRepository.save(pedido);
	}

	public Pedido update(long idPedido, Pedido pedido) {
		Pedido pedidoBD = this.findById(idPedido);
		pedidoBD.setIdCliente(pedido.getIdCliente());
		pedidoBD.setFecha(pedido.getFecha());
		pedidoBD.setTotal(pedido.getTotal());
		pedidoBD.setMetodo(pedido.getMetodo());
		pedidoBD.setNotas(pedido.getNotas());
		return this.pedidoRepository.save(pedidoBD);
	}

	public void deleteById(long idPedido) {
		this.pedidoRepository.delete(this.findById(idPedido));
	}
}
