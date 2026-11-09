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

import com.daw.persistence.entities.Cliente;
import com.daw.services.ClienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {
	private final ClienteService clienteService;

	@GetMapping
	public List<Cliente> list() {
		return this.clienteService.findAll();
	}

	@GetMapping("/{idCliente}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		return ResponseEntity.ok(this.clienteService.findById(id));
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.clienteService.create(cliente));
	}

	@PutMapping("/{idCliente}")
	public ResponseEntity<Cliente> update(@PathVariable long idCliente, @RequestBody Cliente cliente) {
		return ResponseEntity.ok(clienteService.update(idCliente, cliente));
	}

	@DeleteMapping("/{idCliente}")
	public ResponseEntity<?> delete(@PathVariable long idCliente) {
		this.clienteService.deleteById(idCliente);
		return ResponseEntity.ok().build();
	}
}
