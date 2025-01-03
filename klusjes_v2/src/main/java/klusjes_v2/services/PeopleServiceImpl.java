package klusjes_v2.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klusjes_v2.model.Klant;
import klusjes_v2.model.Klus;
import klusjes_v2.model.Klusjesman;
import klusjes_v2.model.People;
import klusjes_v2.repositories.PeopleRepository;

@Service
public class PeopleServiceImpl implements PeopleService {
	
	@Autowired
	private PeopleRepository repo;
	
	public ArrayList<People> findAllPeople() {
		return (ArrayList<People>) this.repo.findAll();
	}
	
	public Optional<People> getPeopleById(String id) {
		return repo.findById(id);
	}
	
}
