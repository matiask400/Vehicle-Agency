package ar.edu.utn.frc.notificacionesVehiculos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ar.edu.utn.frc.notificacionesVehiculos.repositories")
public class NotificacionesVehiculos {

	public static void main(String[] args) {
		SpringApplication.run(NotificacionesVehiculos.class, args);
	}
}