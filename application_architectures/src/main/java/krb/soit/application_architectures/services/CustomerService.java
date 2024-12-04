package krb.soit.application_architectures.services;

import java.util.ArrayList;

import krb.soit.application_architectures.model.Costumer;

public interface CustomerService {
	public ArrayList<Costumer> findAllCostumers();
	public void addCostumer(Costumer c);
}
