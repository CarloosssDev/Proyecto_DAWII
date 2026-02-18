package cibertec.pe.mapper;

import cibertec.pe.dtos.DetallePedidoRequest;
import cibertec.pe.dtos.DetallePedidoResponse;
import cibertec.pe.model.DetallePedido;
import org.springframework.stereotype.Component;

@Component
public class DetallePedidoMapper {
    public DetallePedido toEntity(DetallePedidoRequest request, Double precioReal) {
        DetallePedido entity = new DetallePedido();
        entity.setIdProducto(request.getIdProducto());
        entity.setCantidad(request.getCantidad());
        entity.setPrecioUnitario(precioReal);
        entity.setSubTotal(precioReal * request.getCantidad());
        return entity;
    }

    public DetallePedidoResponse toResponse(DetallePedido entity, String nombreProducto) {
        DetallePedidoResponse dto = new DetallePedidoResponse();
        dto.setNombreProducto(nombreProducto);
        dto.setCantidad(entity.getCantidad());
        dto.setPrecioUnitario(entity.getPrecioUnitario());
        dto.setSubTotal(entity.getSubTotal());
        return dto;
    }
}