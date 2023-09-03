package com.bgl.bike.simulation;

import com.bgl.bike.simulation.service.BikeSimulationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BikeSimulationApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(BikeSimulationApplication.class, args);
		BikeSimulationService bikeSimulationService = applicationContext.getBean(BikeSimulationService.class);
		bikeSimulationService.start();
	}

}
