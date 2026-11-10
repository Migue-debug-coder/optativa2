package com.daw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pizza;
import com.daw.persistence.repositories.PizzaRepository;
import com.daw.services.exceptions.PizzaException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PizzaService {

	private final PizzaRepository pizzaRepository;

	public List<Pizza> findAll() {
		return this.pizzaRepository.findAll();
	}

	public Pizza findById(long idPizza) {
		if (!this.pizzaRepository.existsById(idPizza)) {
			throw new PizzaException(String.format("La Pizza con id %d no existe", idPizza));
		}
		return this.pizzaRepository.findById(idPizza).get();
	}

	public Pizza create(Pizza pizza) {
		if(pizza.getDisponible() == null) {
			pizza.setDisponible(true);
		}
		pizza.setId(0L);
		return this.pizzaRepository.save(pizza);
	}

	public Pizza update(long idPizza, Pizza pizza) {
		Pizza pizzaBD = this.findById(idPizza);
		pizzaBD.setNombre(pizza.getNombre());
		pizzaBD.setDescripcion(pizza.getDescripcion());
		pizzaBD.setPrecio(pizza.getPrecio());
		pizzaBD.setVegetariana(pizza.isVegetariana());
		pizzaBD.setVegana(pizza.isVegana());
		pizzaBD.setDisponible(pizza.getDisponible());
		return this.pizzaRepository.save(pizzaBD);
	}

	public void deleteById(long idPizza) {
		this.pizzaRepository.delete(this.findById(idPizza));
	}
}
