package com.daw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pizza;
import com.daw.persistence.repositories.PizzaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PizzaService {
	private final PizzaRepository pizzaRepository;
	
	public List<Pizza> findAll(){
		return this.pizzaRepository.findAll();
	}
}
