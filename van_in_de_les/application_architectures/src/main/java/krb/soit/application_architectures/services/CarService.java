package krb.soit.application_architectures.services;

import java.util.List;

import krb.soit.application_architectures.model.Car;

public interface CarService {
	public List<Car> getCars();
	public void deleteCar(int carID);
	public void addCar(Car c);
}
