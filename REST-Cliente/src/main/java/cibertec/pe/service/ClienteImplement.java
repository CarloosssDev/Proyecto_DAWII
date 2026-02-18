package cibertec.pe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.pe.model.Cliente;
import cibertec.pe.repository.IClienteRepository;

@Service
public class ClienteImplement implements IClienteService {

	@Autowired
	private IClienteRepository repository;

	@Override
	public List<Cliente> listAll() {
		return repository.findAll();
	}

	public Cliente create(Cliente cliente) {
		if (repository.findByTelefono(cliente.getTelefono()).isPresent()) {
			throw new RuntimeException("El teléfono " + cliente.getTelefono() + " ya está registrado.");
		}
		return repository.save(cliente);
	}

	@Override
	public Cliente update(Long id, Cliente cliente) {
		return repository.findById(id).map(existingClient -> {
			Optional<Cliente> clienteConMismoTelef = repository.findByTelefono(cliente.getTelefono());

			if (clienteConMismoTelef.isPresent() && !clienteConMismoTelef.get().getId().equals(id)) {
				throw new RuntimeException("El teléfono ya pertenece a otro cliente.");
			}

			existingClient.setDireccion(cliente.getDireccion());
			existingClient.setNombre(cliente.getNombre());
			existingClient.setTelefono(cliente.getTelefono());

			return repository.save(existingClient);
		}).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
	}

	@Override
	public String delete(Long id) {
		Cliente c = repository.findById(id).orElseThrow(
				() -> new RuntimeException("No se pudo eliminar: El cliente con ID " + id + " no existe."));

		repository.delete(c);

		return "Cliente con ID " + id + " eliminado correctamente";
	}

	@Override
	public Optional<Cliente> findByTelefono(String telefono) {
		return repository.findByTelefono(telefono);
	}

}
