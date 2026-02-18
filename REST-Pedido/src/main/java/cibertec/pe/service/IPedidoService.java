package cibertec.pe.service;

import java.util.List;

import cibertec.pe.dtos.PedidoRequest;
import cibertec.pe.dtos.PedidoResponse;
import cibertec.pe.enums.EstadoPedido;
import cibertec.pe.enums.MetodoPago;
import cibertec.pe.model.Pedido;

public interface IPedidoService {
    PedidoResponse registrarPedido(PedidoRequest pedido);

    List<PedidoResponse> listarPedidos();

    Pedido buscarPorId(Long id);

    Pedido actualizarPedido(Long id, PedidoRequest pedido);

    Pedido cambiarEstado(Long id, EstadoPedido nuevoEstado);

    Pedido registrarPagoFinal(Long id, MetodoPago pago);

    List<Pedido> listarPorEstado(EstadoPedido estado);

    List<Pedido> listarPorRepartidor(Long idRepartidor);

    List<Pedido> listarPorTelefono(String telefono);
}