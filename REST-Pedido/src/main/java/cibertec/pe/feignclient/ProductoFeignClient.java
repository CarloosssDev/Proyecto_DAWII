package cibertec.pe.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cibertec.pe.entity.Producto;

@FeignClient(name = "REST-Producto", url = "http://localhost:9003")
public interface ProductoFeignClient {
    @GetMapping("/api/productos/{id}")
    Producto obtenerPorId(@PathVariable("id") Long id);
}