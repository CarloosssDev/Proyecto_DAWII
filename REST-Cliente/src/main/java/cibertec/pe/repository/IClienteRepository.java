package cibertec.pe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cibertec.pe.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
	Optional<Cliente>		findByTelefono(String telefono);
}
