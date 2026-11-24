package com.daw.services.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.daw.persistence.entities.Cliente;
import com.daw.persistence.entities.Pedido;
import com.daw.services.dtos.PedidoInputDTO;
import com.daw.services.dtos.PedidoOutputDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PedidoMapper {
	private final PizzaPedidoMapper pizzaPedidoMapper;

	//Método de salida (genera un Output)
	public PedidoOutputDTO toDto(Pedido entity) {
		if(entity == null) {
			return null;
		}
		
		PedidoOutputDTO dto = new PedidoOutputDTO();
		dto.setId(entity.getId());
		dto.setClienteId(entity.getCliente().getId());
		dto.setNombreCliente(entity.getCliente().getNombre());
		dto.setTelefono(entity.getCliente().getTelefono());
		dto.setDireccion(entity.getCliente().getDireccion());
		dto.setFecha(entity.getFecha());
		dto.setTotal(entity.getTotal());
		dto.setMetodo(entity.getMetodo());
		dto.setNotas(entity.getNotas());
		dto.setPizzas(this.pizzaPedidoMapper.toDtos(entity.getPizzas()));

		return dto;
	}
	
	//Método de entrada (genera un entity)
	public Pedido toEntity(PedidoInputDTO dto) {
		if(dto == null) {
			return null;
		}
		
		Cliente cliente = new Cliente();
		cliente.setId(dto.getClienteId());
		
		Pedido entity = new Pedido();
		entity.setMetodo(dto.getMetodo());
		entity.setNotas(dto.getNotas());
		entity.setCliente(cliente);

		return entity;
	}
	
	public List<PedidoOutputDTO> toDtos(List<Pedido> entities) {
		return entities.stream()
				.map(p -> this.toDto(p))
				.toList();
	}
	
	public List<Pedido> toEntities(List<PedidoInputDTO> dtos) {
		return dtos.stream()
				.map(p -> this.toEntity(p))
				.toList();
	}
}
