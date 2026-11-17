package com.daw.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.daw.persistence.entities.Pizza;
import com.daw.persistence.repositories.PizzaRepository;
import com.daw.services.exceptions.ResourceNotFoundException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class PizzaService {

private final PizzaRepository pizzaRepository;
	
	public List<Pizza> findAll(){
		return this.pizzaRepository.findAll();
	}
	
	public Pizza findById(long idPizza) {
		if(!this.pizzaRepository.existsById(idPizza)) {
			throw new ResourceNotFoundException("Pizza no encontrada con ID: " + idPizza);
		}
		
		return this.pizzaRepository.findById(idPizza).get();
	}
	
	public Pizza create(@Valid Pizza pizza) {		
		if(pizza.getDisponible() == null) {
			pizza.setDisponible(true);
		}	
		if(pizza.getVegana() == null) {
			pizza.setVegana(false);
		}	
		if(pizza.getVegetariana() == null) {
			pizza.setVegetariana(false);
		}
		
		pizza.setId(null);
		
		return this.pizzaRepository.save(pizza);
	}
	
	public Pizza update(long idPizza, @Valid Pizza pizzaEntity) {		
		Pizza pizzaBD = this.findById(idPizza);
		
		pizzaBD.setDescripcion(pizzaEntity.getDescripcion());
		pizzaBD.setDisponible(pizzaEntity.getDisponible());
		pizzaBD.setNombre(pizzaEntity.getNombre());
		pizzaBD.setPrecio(pizzaEntity.getPrecio());
		pizzaBD.setVegana(pizzaEntity.getVegana());
		pizzaBD.setVegetariana(pizzaEntity.getVegetariana());
		
		return this.pizzaRepository.save(pizzaBD);
	}
	
	public void deleteById(long idPizza) {
		if(!this.pizzaRepository.existsById(idPizza)) {
			throw new ResourceNotFoundException("No se puede eliminar. Pizza no encontrada con ID: " + idPizza);
		}
		
		this.pizzaRepository.deleteById(idPizza);
	}
}
