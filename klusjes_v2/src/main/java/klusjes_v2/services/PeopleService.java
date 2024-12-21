package klusjes_v2.services;

import java.util.ArrayList;

import klusjes_v2.model.People;

public interface PeopleService {
	public ArrayList<People> findAllPeople();
	public People getPeopleById(String id);
}
