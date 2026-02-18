package cibertec.pe.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cibertec.pe.model.Pedido;
import cibertec.pe.dtos.PedidoRequest;
import cibertec.pe.dtos.PedidoResponse;
import cibertec.pe.enums.EstadoPedido;
import cibertec.pe.enums.MetodoPago;
import cibertec.pe.service.IPedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private IPedidoService service;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@Valid @RequestBody PedidoRequest pedido) {
        try {
            PedidoResponse nuevo = service.registrarPedido(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<PedidoResponse>> listAll() {
        return ResponseEntity.ok(service.listarPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @RequestParam EstadoPedido estado) {
        try {
            return ResponseEntity.ok(service.cambiarEstado(id, estado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/pago")
    public ResponseEntity<?> registrarPago(@PathVariable Long id, @RequestParam MetodoPago pago) {
        try {
            return ResponseEntity.ok(service.registrarPagoFinal(id, pago));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/repartidor/{idRepartidor}")
    public ResponseEntity<List<Pedido>> listarPorRepartidor(@PathVariable Long idRepartidor) {
        return ResponseEntity.ok(service.listarPorRepartidor(idRepartidor));
    }
}