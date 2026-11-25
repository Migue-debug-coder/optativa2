package com.daw.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.daw.persistence.entities.Cliente;
import com.daw.persistence.entities.Pedido;
import com.daw.persistence.repositories.ClienteRepository;
import com.daw.persistence.repositories.PedidoRepository;
import com.daw.services.dtos.PedidoInputDTO;
import com.daw.services.dtos.PedidoOutputDTO;
import com.daw.services.exceptions.ResourceNotFoundException;
import com.daw.services.mappers.PedidoMapper;

import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
@Transactional
public class PedidoService {

private final PedidoRepository pedidoRepository;
private final PedidoMapper pedidoMapper;
private final ClienteRepository clienteRepository;
	
	@Transactional(readOnly = true)
	public List<PedidoOutputDTO> findAll(){
		return this.pedidoMapper.toDtos(pedidoRepository.findAll());
	}
	
	@Transactional(readOnly = true)
	public PedidoOutputDTO findById(long idPedido) {
		if (!this.pedidoRepository.existsById(idPedido)) {
			throw new ResourceNotFoundException("Pedido no encontrado con ID: " + idPedido);
		}

		return this.pedidoMapper.toDto(pedidoRepository.findById(idPedido).get());
	}
	
	public PedidoOutputDTO create(@Valid PedidoInputDTO inputDto) {
		// En el inputDTO recibo el id cliente método y notas
		Cliente cliente = this.clienteRepository.findById(inputDto.getClienteId())
		.orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: "+inputDto.getClienteId()));
		
		Pedido pedido = new Pedido();
		pedido.setId(null);
		pedido.setFecha(LocalDateTime.now());
		pedido.setTotal(0.0);
		pedido.setMetodo(inputDto.getMetodo());
		pedido.setNotas(inputDto.getNotas());
		pedido.setCliente(cliente);
				
		return this.pedidoMapper.toDto(this.pedidoRepository.save(pedido));
	}

	public Pedido update(long idPedido, @Valid PedidoInputDTO inputDto) {
		Pedido pedidoBD = this.pedidoRepository.findById(idPedido)
				.orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado con ID: "+ idPedido));
		
		Cliente cliente = this.clienteRepository.findById(inputDto.getClienteId())
				.orElseThrow(() -> new ResourceNotFoundException("No se puede crear el pedido. Cliente no encontrado con ID: " +inputDto.getClienteId()));
		
		
		pedidoBD.setMetodo(inputDto.getMetodo());
		pedidoBD.setNotas(inputDto.getNotas());
        pedidoBD.setCliente(cliente);

		return this.pedidoRepository.save(pedidoBD);
	}

	public void deleteById(long idPedido) {
		if (!this.pedidoRepository.existsById(idPedido)) {
			throw new ResourceNotFoundException("No se puede eliminar. Pedido no encontrado con ID: " + idPedido);
		}

		this.pedidoRepository.deleteById(idPedido);
	}
}
