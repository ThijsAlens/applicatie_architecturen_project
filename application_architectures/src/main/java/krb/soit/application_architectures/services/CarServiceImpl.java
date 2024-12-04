package krb.soit.application_architectures.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import krb.soit.application_architectures.model.Car;
import krb.soit.application_architectures.repositories.CarRepository;

@Service
public class CarServiceImpl implements CarService{
	@Autowired
	private CarRepository carRepo;
	
	public List<Car> getCars() {
		return (List<Car>) carRepo.findAll();
	}
	
	public void deleteCar(int carID) {
		carRepo.deleteById(carID);
	}
	
	public void addCar(Car c) {
		carRepo.save(c);
	}
}
