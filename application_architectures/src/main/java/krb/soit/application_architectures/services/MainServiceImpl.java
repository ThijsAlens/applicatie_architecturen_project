package krb.soit.application_architectures.services;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import krb.soit.application_architectures.model.Car;
import krb.soit.application_architectures.model.Costumer;
import krb.soit.application_architectures.model.Location;
import krb.soit.application_architectures.repositories.CarRepository;
import krb.soit.application_architectures.repositories.CostumerRepository;
import krb.soit.application_architectures.repositories.LocationRepository;

@Service
public class MainServiceImpl implements MainService {
	@Autowired
	private CarRepository carRepo;
	
	@Autowired
	private LocationRepository locationRepo;
	
	public ArrayList<Car> findAllCars() {
		return (ArrayList<Car>) carRepo.findAll();
	}
	
	public ArrayList<Location> findAllLocations() {
		return (ArrayList<Location>) locationRepo.findAll();
	}
}
