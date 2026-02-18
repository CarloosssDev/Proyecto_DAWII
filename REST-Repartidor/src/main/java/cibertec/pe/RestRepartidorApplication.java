package cibertec.pe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RestRepartidorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestRepartidorApplication.class, args);
	}

}
