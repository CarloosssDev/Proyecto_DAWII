package cibertec.pe.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cibertec.pe.entity.Repartidor;

@FeignClient(name = "REST-Repartidor", url = "http://localhost:9004")
public interface RepartidorFeignClient {
    @GetMapping("/api/repartidores/{id}")
    Repartidor obtenerPorId(@PathVariable("id") Long id);
}