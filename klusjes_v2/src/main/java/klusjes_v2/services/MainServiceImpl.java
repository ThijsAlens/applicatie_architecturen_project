package klusjes_v2.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klusjes_v2.model.Klus;
import klusjes_v2.model.Klusje;
import klusjes_v2.model.People;
import klusjes_v2.repositories.KlusjesRepository;
import klusjes_v2.repositories.PeopleRepository;

@Service
public class MainServiceImpl implements MainService{

	@Autowired
	private KlusjesRepository repo;
	
	public ArrayList<Klus> getAllKlusjes() {
		return (ArrayList<Klus>) repo.findAll();
	}
	
	public Klus getKlusjeById(int id) {
		return (Klus)repo.getById(id);
	}
	
	public void updateKlusjeById(Klus k) {
		repo.deleteById(k.getId());
		repo.saveAndFlush(k);
	}
}
