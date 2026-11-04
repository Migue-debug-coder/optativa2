package com.daw.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<List<Cliente>> list(){
		return ResponseEntity.ok(this.clienteService.findAll());
	}
}
