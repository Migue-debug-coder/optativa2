package com.daw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Cliente;
import com.daw.persistence.repositories.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository clienteRepository;

	public List<Cliente> findAll() {
		return this.clienteRepository.findAll();
	}
}
