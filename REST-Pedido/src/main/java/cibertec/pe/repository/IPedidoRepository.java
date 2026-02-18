package cibertec.pe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cibertec.pe.enums.EstadoPedido;
import cibertec.pe.model.Pedido;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByEstado(EstadoPedido estado);
    List<Pedido> findByIdRepartidor(Long idRepartidor);
    List<Pedido> findByTelefonoCliente(String telefonoCliente);
}