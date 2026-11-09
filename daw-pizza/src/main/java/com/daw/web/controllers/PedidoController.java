package com.daw.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Pedido;
import com.daw.services.PedidoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {
	private final PedidoService pedidoService;

	@GetMapping
	public List<Pedido> list() {
		return this.pedidoService.findAll();
	}

	@GetMapping("/{idPedido}")
	public ResponseEntity<?> findById(@PathVariable long idPedido) {
		return ResponseEntity.ok(this.pedidoService.findById(idPedido));
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Pedido pedido) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.pedidoService.create(pedido));
	}

	@PutMapping("/{idPedido}")
	public ResponseEntity<Pedido> update(@PathVariable long idPedido, @RequestBody Pedido pedido) {
		return ResponseEntity.ok(pedidoService.update(idPedido, pedido));
	}

	@DeleteMapping("/{idPedido}")
	public ResponseEntity<?> delete(@PathVariable long idPedido) {
		this.pedidoService.deleteById(idPedido);
		return ResponseEntity.ok().build();
	}
}
