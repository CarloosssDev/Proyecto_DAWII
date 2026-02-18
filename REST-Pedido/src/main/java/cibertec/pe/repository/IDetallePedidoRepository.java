package cibertec.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cibertec.pe.model.DetallePedido;

public interface IDetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

}
