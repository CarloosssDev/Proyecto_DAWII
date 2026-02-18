package cibertec.pe.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DetallePedidoRequest {

    @NotNull(message = "El ID del producto es obligatorio")

    private Long idProducto;
    @Min(value = 1, message = "La cantidad mínima es 1")

    private int cantidad;

    public DetallePedidoRequest() {
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
