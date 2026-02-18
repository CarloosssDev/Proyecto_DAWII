package cibertec.pe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cibertec.pe.model.Producto;
import cibertec.pe.service.IProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	@Autowired
	private IProductoService service;

	@GetMapping("/listar")
	public ResponseEntity<List<Producto>> listar() {
		return ResponseEntity.ok(service.listarProductos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtener(@PathVariable Long id) {
		Optional<Producto> producto = service.obtenerPorId(id);

		if (producto.isPresent()) {
			return ResponseEntity.ok(producto.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("El producto con ID " + id + " no existe en el catálogo.");
		}
	}

	@PostMapping("/crear")
	public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
		Producto nuevo = service.crearProducto(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
	}
}