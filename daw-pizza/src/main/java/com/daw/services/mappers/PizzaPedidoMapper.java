package com.daw.services.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.daw.persistence.entities.Pizza;
import com.daw.persistence.entities.PizzaPedido;
import com.daw.services.dtos.PizzaPedidoInputDTO;
import com.daw.services.dtos.PizzaPedidoOutputDTO;

@Component
public class PizzaPedidoMapper {
	public PizzaPedidoOutputDTO toDto(PizzaPedido entity) {
		if(entity == null) {
			return null;
		}
		
		PizzaPedidoOutputDTO dto = new PizzaPedidoOutputDTO();
		dto.setId(entity.getId());
		dto.setPizzaId(entity.getPizza().getId());
		dto.setPedidoId(entity.getPedido().getId());
		dto.setNombre(entity.getPizza().getNombre());
		dto.setCantidad(entity.getCantidad());
		dto.setPrecioUnitario(entity.getPizza().getPrecio());
		dto.setPrecioTotal(entity.getCantidad() * entity.getPrecio());

		return dto;
	}
	
	public PizzaPedido toEntity(PizzaPedidoInputDTO dto) {
		if(dto == null) {
			return null;
		}
		
		Pizza pizza = new Pizza();
		pizza.setId(dto.getPizzaId());
		
		PizzaPedido entity = new PizzaPedido();
		entity.setCantidad(dto.getCantidad());
		entity.setPizza(pizza);

		return entity;
	}
	
	public List<PizzaPedidoOutputDTO> toDtos(List<PizzaPedido> entities) {
		return entities.stream()
				.map(pp -> this.toDto(pp))
				.toList();
	}
	
	public List<PizzaPedido> toEntities(List<PizzaPedidoInputDTO> dtos) {
		return dtos.stream()
				.map(pp -> this.toEntity(pp))
				.toList();
	}

}
