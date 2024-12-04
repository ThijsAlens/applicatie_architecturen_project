package krb.soit.application_architectures.services;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import krb.soit.application_architectures.model.Costumer;
import krb.soit.application_architectures.repositories.CostumerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CostumerRepository costumerRepo;
	
	public ArrayList<Costumer> findAllCostumers() {
		return (ArrayList<Costumer>) costumerRepo.findAll();
	}
	
	public void addCostumer(Costumer c) {
		costumerRepo.save(c);
	}
	
}
