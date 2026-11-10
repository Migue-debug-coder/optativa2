package com.daw.web.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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

}
