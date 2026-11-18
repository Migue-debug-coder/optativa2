package com.daw.services.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {
	
	private Long id;
	
	@NotBlank(message = "El nombre del cliente no puede estar vacío")
	@Size(max = 30,message = "El nombre no puede superar los 30 carcateres")
	private String nombre;
	
	@NotBlank(message = "La dirección no puede estar vacía")
	@Size(max = 100,message = "La dirección no puede superar los 100 carcateres")
	private String direccion;

	@NotBlank(message = "El email no puede estar vacío")
	@Email(message = "El email no tiene un formato válido")
	@Size(max = 50,message = "El email no puede superar los 50 carcateres")
	private String email;

	@NotBlank(message = "El teléfono no puede estar vacío")
	@Size(max = 20,message = "El teléfono no puede superar los 20 carcateres")
	private String telefono;
}
