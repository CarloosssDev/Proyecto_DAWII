package cibertec.pe.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cibertec.pe.entity.Cliente;

@FeignClient(name = "REST-Cliente", url = "http://localhost:9001")
public interface ClienteFeignClient {
    @GetMapping("/api/clientes/telefono/{telefono}")
    Cliente buscarPorTelefono(@PathVariable("telefono") String telefono);
}