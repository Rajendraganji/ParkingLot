package com.squadstack.Parkinglot;

import com.squadstack.Parkinglot.controller.ParkingLotController;
import com.squadstack.Parkinglot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingLotApplication implements ApplicationRunner {

	private final ParkingLotService parkinglotservice;

// Get input file location from command line arguements
	@Value("${inputfile.path}")
	private String filePath;

	public ParkingLotApplication(ParkingLotService parkinglotservice) {
		this.parkinglotservice = parkinglotservice;
	}

	public static void main(String[] args) {
		SpringApplication.run(ParkingLotApplication.class, args);
	}

	@Override
	public void run( ApplicationArguments args ) {
		ParkingLotController parkinglotcontroller=new ParkingLotController(parkinglotservice);
		parkinglotcontroller.processInputFile(filePath);
	}

}
