package krb.soit.application_architectures.services;

import java.util.ArrayList;

import krb.soit.application_architectures.model.Car;
import krb.soit.application_architectures.model.Location;

public interface MainService {
	public ArrayList<Location> findAllLocations();
	public ArrayList<Car> findAllCars();
}
