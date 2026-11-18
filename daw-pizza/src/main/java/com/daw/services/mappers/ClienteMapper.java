package com.daw.services.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.daw.persistence.entities.Cliente;
import com.daw.services.dtos.ClienteDTO;

@Component
public class ClienteMapper {
	public ClienteDTO toDto(Cliente cliente) {
		if(cliente == null) {
			return null;
		}
		
		ClienteDTO dto = new ClienteDTO();
		dto.setId(cliente.getId());
		dto.setNombre(cliente.getNombre());
		dto.setEmail(cliente.getEmail());
		dto.setDireccion(cliente.getDireccion());
		dto.setTelefono(cliente.getTelefono());
		
		return dto;
	}
	
	public Cliente toEntity(ClienteDTO dto) {
		if(dto == null) {
			return null;
		}
		
		Cliente cliente = new Cliente();
		cliente.setId(dto.getId());
		cliente.setNombre(dto.getNombre());
		cliente.setEmail(dto.getEmail());
		cliente.setDireccion(dto.getDireccion());
		cliente.setTelefono(dto.getTelefono());
		
		return cliente;
	}
	
	public List<ClienteDTO> toDtos(List<Cliente> entities){
		return entities.stream()
				.map(c -> this.toDto(c))
				.toList();
	}
	
	public List<Cliente> toEntities(List<ClienteDTO> dtos){
		return dtos.stream()
				.map(c -> this.toEntity(c))
				.toList();
	}
}
