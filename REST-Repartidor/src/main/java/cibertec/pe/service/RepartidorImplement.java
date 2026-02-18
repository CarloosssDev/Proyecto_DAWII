package cibertec.pe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cibertec.pe.model.Repartidor;
import cibertec.pe.repository.IRepartidorRepository;
@Service
public class RepartidorImplement implements IRepartidorService {

    @Autowired
    private IRepartidorRepository repository;

    @Override
    public List<Repartidor> listarRepartidores() {
        return repository.findAll();
    }

    @Override
    public Repartidor crearRepartidor(Repartidor repartidor) {
        return repository.save(repartidor);
    }

    @Override
    public Optional<Repartidor> buscarPorId(Long id) {
        return repository.findById(id);
    }
}