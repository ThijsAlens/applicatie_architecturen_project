package klusjes_v2.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klusjes_v2.model.Klus;
import klusjes_v2.model.Klusjesman;
import klusjes_v2.model.People;
import klusjes_v2.repositories.KlusjesRepository;
import klusjes_v2.repositories.PeopleRepository;

@Service
public class KlusServiceImpl implements KlusService{

	@Autowired
	private KlusjesRepository repo;
	
	public ArrayList<Klus> getAllKlusjes() {
		return (ArrayList<Klus>) repo.findAll();
	}
	
	
	public void updateKlusje(Klus k) {
		repo.deleteById(k.getKlusId());
		repo.saveAndFlush(k);
	}
	
	public void addKlus(Klus k) {
		this.repo.save(k);
	}
	
	public void deleteBiedingenByKlusId(int id) {
		//TODO
	}

	public ArrayList<Klusjesman> getGebodenKlusjesmannenByKlusId(int klusId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setGebodenKlusjesmannenByKlusId(int klusId, ArrayList<Klusjesman> km) {
		// TODO Auto-generated method stub
		
	}

	public void deleteKlusByKlusId(int klusId) {
		// TODO Auto-generated method stub
	}
		
	 public Klus getKlusById(int klusId) {
	        return repo.findById(klusId).orElse(null);
	    
	}
	 
	 public float getRatingByKlusjesmanId(int id) {
			ArrayList<Klus> klussen = (ArrayList<Klus>) repo.findAll();
			if (klussen.size() == 0)
				return -1;
			int count = 0;
			int sum = 0;
			for (Klus klus : klussen) {
				if (klus.getKlusjesman().getKlusjesmanId() == id) {
					count++;
					sum += klus.getRating();
				}
			}
			return sum / count;
		}

}
