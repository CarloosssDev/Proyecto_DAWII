package cibertec.pe.service;

import java.util.List;
import java.util.Optional;

import cibertec.pe.model.Producto;

public interface IProductoService {
	List<Producto>			listarProductos();
	Producto 				crearProducto(Producto producto);
	Optional<Producto>		obtenerPorId(Long id);	
}
