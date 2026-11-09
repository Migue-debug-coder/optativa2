package com.daw.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.daw.persistence.entities.Cliente;
import com.daw.persistence.repositories.ClienteRepository;
import com.daw.services.exceptions.ClienteException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {
	private final ClienteRepository clienteRepository;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findById(long id) {
		if (!clienteRepository.existsById(id)) {
			throw new ClienteException(String.format("El cliente con id %d no existe", id));
		}
		return clienteRepository.findById(id).get();
	}

	public Cliente create(Cliente cliente) {
		cliente.setId(0L);
		return clienteRepository.save(cliente);
	}

	public Cliente update(long id, Cliente cliente) {
		Cliente clienteBD = findById(id);
		clienteBD.setNombre(cliente.getNombre());
		clienteBD.setDireccion(cliente.getDireccion());
		clienteBD.setEmail(cliente.getEmail());
		clienteBD.setTelefono(cliente.getTelefono());
		return clienteRepository.save(clienteBD);
	}

	public void deleteById(long id) {
		this.clienteRepository.delete(findById(id));
	}
}
