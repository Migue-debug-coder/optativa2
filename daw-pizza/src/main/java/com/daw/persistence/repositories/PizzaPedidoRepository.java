package com.daw.persistence.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.persistence.entities.PizzaPedido;

public interface PizzaPedidoRepository extends JpaRepository<PizzaPedido, Long>{
	  List<PizzaPedido> findByPedidoId(Long pedidoId);
	    
	  Optional<PizzaPedido> findByIdAndPedidoId(Long id, Long pedidoId);

}
