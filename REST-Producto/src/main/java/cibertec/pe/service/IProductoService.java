package cibertec.pe.service;

import java.util.List;
import java.util.Optional;

import cibertec.pe.model.Producto;

public interface IProductoService {
	List<Producto> listarProductos();

	Producto crearProducto(Producto producto);

	String actualizarProducto(Long id, Producto producto);

	String eliminarProducto(Long id);

	Optional<Producto> obtenerPorId(Long id);
}
