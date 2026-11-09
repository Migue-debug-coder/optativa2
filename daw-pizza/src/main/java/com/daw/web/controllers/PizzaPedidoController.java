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

import com.daw.persistence.entities.PizzaPedido;
import com.daw.services.PizzaPedidoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pizza_pedidos")
@RequiredArgsConstructor
public class PizzaPedidoController {
	private final PizzaPedidoService pizzaPedidoService;

	@GetMapping
	public List<PizzaPedido> list() {
		return this.pizzaPedidoService.findAll();
	}

	@GetMapping("/{idPizzaPedido}")
	public ResponseEntity<?> findById(@PathVariable long idPizzaPedido) {
		return ResponseEntity.ok(this.pizzaPedidoService.findById(idPizzaPedido));
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody PizzaPedido pizzaPedido) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.pizzaPedidoService.create(pizzaPedido));
	}

	@PutMapping("/{idPizzaPedido}")
	public ResponseEntity<PizzaPedido> update(@PathVariable long idPizzaPedido, @RequestBody PizzaPedido pizzaPedido) {
		return ResponseEntity.ok(pizzaPedidoService.update(idPizzaPedido, pizzaPedido));
	}

	@DeleteMapping("/{idPizzaPedido}")
	public ResponseEntity<?> delete(@PathVariable long idPizzaPedido) {
		this.pizzaPedidoService.deleteById(idPizzaPedido);
		return ResponseEntity.ok().build();
	}
}
