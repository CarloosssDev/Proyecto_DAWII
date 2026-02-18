package cibertec.pe.mapper;

import cibertec.pe.dtos.DetallePedidoResponse;
import cibertec.pe.dtos.PedidoRequest;
import cibertec.pe.dtos.PedidoResponse;
import cibertec.pe.enums.EstadoPedido;
import cibertec.pe.enums.MetodoPago;
import cibertec.pe.model.Pedido;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class PedidoMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Pedido toEntity(PedidoRequest request) {
        Pedido pedido = new Pedido();
        pedido.setTelefonoCliente(request.getTelefonoCliente());
        pedido.setIdRepartidor(request.getIdRepartidor());

        pedido.setFechaRegistro(LocalDateTime.now());
        pedido.setEstado(EstadoPedido.PENDIENTE);
        pedido.setMetodoPago(MetodoPago.EFECTIVO);

        return pedido;
    }

    /**
     * @param entity           La entidad Pedido que queremos convertir a DTO
     * @param nombreCliente    Dato traído de Feign Cliente
     * @param direccion        Dato traído de Feign Cliente
     * @param nombreRepartidor Dato traído de Feign Repartidor
     * @param detalles         La lista de detalles ya convertidos a DTOs
     */
    public PedidoResponse toResponse(Pedido entity,
            String nombreCliente,
            String direccion,
            String nombreRepartidor,
            List<DetallePedidoResponse> detalles) {

        PedidoResponse dto = new PedidoResponse();

        dto.setCliente(nombreCliente);
        dto.setDireccion(direccion);
        dto.setRepartidor(nombreRepartidor);

        dto.setEstado(entity.getEstado().name());
        dto.setMetodoPago(entity.getMetodoPago().name());
        dto.setTotal(entity.getMontoTotal());

        if (entity.getFechaRegistro() != null) {
            dto.setFecha(entity.getFechaRegistro().format(FORMATTER));
        }

        if (detalles != null) {
            dto.setDetalles(detalles.toArray(new DetallePedidoResponse[0]));
        }

        return dto;
    }
}