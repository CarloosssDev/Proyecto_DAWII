package cibertec.pe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.pe.model.Producto;
import cibertec.pe.repository.IProductoRepository;

@Service
public class ProductoImplement implements IProductoService {

	@Autowired
	private IProductoRepository repository;

	@Override
	public List<Producto> listarProductos() {
		return repository.findAll();
	}

	@Override
	public Producto crearProducto(Producto producto) {
		return repository.save(producto);
	}

	@Override
	public Optional<Producto> obtenerPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	public String actualizarProducto(Long id, Producto producto) {
		Optional<Producto> productoOptional = repository.findById(id);
		if (productoOptional.isPresent()) {
			Producto productoExistente = productoOptional.get();
			productoExistente.setNombre(producto.getNombre());
			productoExistente.setPrecio(producto.getPrecio());
			repository.save(productoExistente);
			return "Producto actualizado correctamente";
		} else {
			return "Producto no encontrado";
		}
	}

	@Override
	public String eliminarProducto(Long id) {
		Optional<Producto> productoOptional = repository.findById(id);
		if (productoOptional.isPresent()) {
			repository.deleteById(id);
			return "Producto eliminado correctamente";
		} else {
			return "Producto no encontrado";
		}
	}

}
