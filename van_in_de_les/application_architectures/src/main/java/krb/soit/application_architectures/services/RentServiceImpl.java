package krb.soit.application_architectures.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import krb.soit.application_architectures.model.Rent;
import krb.soit.application_architectures.repositories.RentRepository;

@Service
public class RentServiceImpl implements RentService{
	
	@Autowired
	private RentRepository rentRepo;
	
	public float berekenPrijs(int numberOfDays, int priceOfTheDay) {
		int p = numberOfDays * (priceOfTheDay + 1000);
		return (float) (p * 1.21);
	}
	
	public void addRent(Rent r) {
		rentRepo.save(r);
	}
	
	public ArrayList<Rent> findAllRents() {
		return (ArrayList<Rent>) rentRepo.findAll();
	}
	
	public void deleteRent(Rent r) {
		rentRepo.delete(r);
	}
	
}
