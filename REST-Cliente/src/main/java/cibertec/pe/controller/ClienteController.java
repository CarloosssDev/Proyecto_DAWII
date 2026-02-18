package cibertec.pe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cibertec.pe.model.Cliente;
import cibertec.pe.service.IClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private IClienteService service;

	@GetMapping("/list")
	public ResponseEntity<List<Cliente>> listAll() {
		return ResponseEntity.ok(service.listAll());
	}

	@GetMapping("/telefono/{telefono}")
	public ResponseEntity<?> findByTelefono(@PathVariable String telefono) {
		Optional<Cliente> clienteOpt = service.findByTelefono(telefono);

		if (clienteOpt.isPresent()) {
			return ResponseEntity.ok(clienteOpt.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Cliente con teléfono " + telefono + " no encontrado");
		}
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente) {
		try {
			Cliente nuevo = service.create(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		try {
			Cliente clienteActualizado = service.update(id, cliente);
			return ResponseEntity.ok(clienteActualizado);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			String respuesta = service.delete(id);
			return ResponseEntity.ok(respuesta);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}