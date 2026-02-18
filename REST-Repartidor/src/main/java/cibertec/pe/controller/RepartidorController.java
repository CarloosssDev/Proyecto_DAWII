package cibertec.pe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cibertec.pe.model.Repartidor;
import cibertec.pe.service.IRepartidorService;

@RestController
@RequestMapping("/api/repartidores")
public class RepartidorController {

    @Autowired
    private IRepartidorService service;

    @GetMapping("/listar")
    public ResponseEntity<List<Repartidor>> listar() {
        return ResponseEntity.ok(service.listarRepartidores());
    } 

    @PostMapping("/crear")
    public ResponseEntity<Repartidor> crear(@RequestBody Repartidor repartidor) {
        Repartidor nuevo = service.crearRepartidor(repartidor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<Repartidor> repartidor = service.buscarPorId(id);
        
        if (repartidor.isPresent()) {
            return ResponseEntity.ok(repartidor.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El repartidor con ID " + id + " no está registrado en el sistema.");
        }
    }
}