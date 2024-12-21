package klusjes_v2.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klusjes_v2.model.People;
import klusjes_v2.repositories.PeopleRepository;

@Service
public class PeopleServiceImpl implements PeopleService {
	
	@Autowired
	private PeopleRepository repo;
	
	public ArrayList<People> findAllPeople() {
		return (ArrayList<People>) this.repo.findAll();
	}
	
	public People getPeopleById(String id) {
		return this.repo.getById(id);
	}
}
