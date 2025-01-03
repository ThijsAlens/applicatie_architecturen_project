package klusjes_v2.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klusjes_v2.model.Klant;
import klusjes_v2.model.Klus;
import klusjes_v2.model.Klusjesman;
import klusjes_v2.model.People;
import klusjes_v2.repositories.KlantRepository;
import klusjes_v2.repositories.PeopleRepository;

@Service
public class KlantServiceImpl implements KlantService {
	
	@Autowired
	private KlantRepository repo;
	
    public Optional<Klant> getKlantByUsername(String username) {
        return repo.findByPeople_Username(username);
    }
	
	public Optional<Klant> getKlantById(Integer id) {
		return repo.findById(id);
	}

	public void updateKlant(Klant k) {
		repo.deleteById(k.getKlantId());;
		repo.save(k);
	}
	
}