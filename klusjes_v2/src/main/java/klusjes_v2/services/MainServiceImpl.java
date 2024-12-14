package klusjes_v2.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import klusjes_v2.model.People;

@Service
public class MainServiceImpl implements MainService{

	public ArrayList<People> findAllPeople() {
		// get all the people out of the database
		return null;
	}
}
