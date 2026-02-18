package cibertec.pe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cibertec.pe.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	public Optional<Usuario> findByEmail(String email);
}
