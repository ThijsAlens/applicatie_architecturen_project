package krb.soit.application_architectures.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import krb.soit.application_architectures.model.Car;
import krb.soit.application_architectures.services.CarService;

@RestController
public class Restctrl {
	@Autowired
	private CarService serv;
	
	@GetMapping("/cars")
	public List<Car> cars() {
		return serv.getCars();
	}
	
	@DeleteMapping("/deleteCar/{carID}")
	public void deleteCar(@PathVariable int carID) {
		serv.deleteCar(carID);
	}
	
	@PostMapping("/addCar")
	public void voegToe(@RequestBody Car c) {
		serv.addCar(c);
	}
}
