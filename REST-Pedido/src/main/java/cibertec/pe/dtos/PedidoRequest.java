package cibertec.pe.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PedidoRequest {
    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(min = 9, max = 9, message = "El teléfono debe tener 9 dígitos")
    private String telefonoCliente;
    private Long idRepartidor;
    private DetallePedidoRequest[] detalles;

    public PedidoRequest() {
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public Long getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(Long idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public DetallePedidoRequest[] getDetalles() {
        return detalles;
    }

    public void setDetalles(DetallePedidoRequest[] detalles) {
        this.detalles = detalles;
    }
}
