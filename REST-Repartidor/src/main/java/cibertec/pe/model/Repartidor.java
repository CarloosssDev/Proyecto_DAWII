package cibertec.pe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_repartidores")
public class Repartidor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "El nombre del repartidor es obligatorio")
	private String nombre;

	@NotBlank(message = "El apellido del repartidor es obligatorio")
	private String apellido;

	public Repartidor() {
	}

	public Repartidor(@NotBlank(message = "El nombre del repartidor es obligatorio") String nombre,
			@NotBlank(message = "El apellido del repartidor es obligatorio") String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}