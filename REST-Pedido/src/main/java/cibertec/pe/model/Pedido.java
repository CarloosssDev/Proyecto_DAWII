package cibertec.pe.model;

import cibertec.pe.enums.EstadoPedido;
import cibertec.pe.enums.MetodoPago;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tbl_pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String telefonoCliente;

	private LocalDateTime fechaRegistro = LocalDateTime.now();

	private Double montoTotal;

	@Enumerated(EnumType.STRING)
	private MetodoPago metodoPago = MetodoPago.EFECTIVO;

	@Enumerated(EnumType.STRING)
	private EstadoPedido estado = EstadoPedido.PENDIENTE;

	private Long idRepartidor;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "pedido_id")
	private List<DetallePedido> detalles;

	public Pedido() {
	}

	public Pedido(String telefonoCliente, LocalDateTime fechaRegistro, Double montoTotal, MetodoPago metodoPago,
			EstadoPedido estado, Long idRepartidor, List<DetallePedido> detalles) {
		this.telefonoCliente = telefonoCliente;
		this.fechaRegistro = fechaRegistro;
		this.montoTotal = montoTotal;
		this.metodoPago = metodoPago;
		this.estado = estado;
		this.idRepartidor = idRepartidor;
		this.detalles = detalles;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public MetodoPago getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(MetodoPago metodoPago) {
		this.metodoPago = metodoPago;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public Long getIdRepartidor() {
		return idRepartidor;
	}

	public void setIdRepartidor(Long idRepartidor) {
		this.idRepartidor = idRepartidor;
	}

	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}
}