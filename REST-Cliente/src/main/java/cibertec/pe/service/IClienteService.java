package cibertec.pe.service;

import java.util.List;
import java.util.Optional;

import cibertec.pe.model.Cliente;

public interface IClienteService {
	List<Cliente>		listAll();
	Cliente				create(Cliente cliente);
	Optional<Cliente>	findByTelefono(String telefono);
	Cliente				update(Long id, Cliente cliente);
	String 				delete(Long id);
}
