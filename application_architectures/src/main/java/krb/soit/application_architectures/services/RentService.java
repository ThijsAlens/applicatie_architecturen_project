package krb.soit.application_architectures.services;

import java.util.ArrayList;

import krb.soit.application_architectures.model.Rent;

public interface RentService {
	public float berekenPrijs(int numberOfDays, int priceOfTheDay);
	public void addRent(Rent r);
	public ArrayList<Rent> findAllRents();
	public void deleteRent(Rent r);
}
