package org.atravieso.springcloud.msvc.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// Habilitar en la aplicación el contexto de feign para poder implementar la API rest que comunica a los microservicios de forma declarativa
@SpringBootApplication
@EnableFeignClients
public class MsvcCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcCursosApplication.class, args);
	}

}
