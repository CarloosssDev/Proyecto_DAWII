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

}
