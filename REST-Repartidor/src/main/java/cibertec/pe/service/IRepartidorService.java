package cibertec.pe.service;

import java.util.List;
import java.util.Optional;

import cibertec.pe.model.Repartidor;

public interface IRepartidorService {
	List<Repartidor>		listarRepartidores();
	Repartidor				crearRepartidor(Repartidor repartidor);
	Optional<Repartidor>	buscarPorId(Long id);
}
