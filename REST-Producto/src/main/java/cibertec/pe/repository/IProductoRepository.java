package cibertec.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cibertec.pe.model.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Long> {

}
