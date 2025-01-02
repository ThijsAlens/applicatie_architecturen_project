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
import klusjes_v2.repositories.KlusjesmanRepository;
import klusjes_v2.repositories.PeopleRepository;

@Service
public class KlusjesmanServiceImpl implements KlusjesmanService {
	
	@Autowired
	private KlusjesmanRepository repo;
	
    public Optional<Klusjesman> getKlusjesmanByUsername(String username) {
        return repo.findByPeople_Username(username);
    }
	
	public Optional<Klusjesman> getKlusjesmanById(Integer id) {
		return repo.findById(id);
	}
	
}