package klusjes_v2.services;

import java.util.ArrayList;

import klusjes_v2.model.Klant;
import klusjes_v2.model.Klusjesman;
import klusjes_v2.model.People;

public interface PeopleService {
	public ArrayList<People> findAllPeople();
	public People getPeopleById(String id);
	public Klusjesman getKlusjesmanById(String id);
	public Klant getKlantById(String id);
}
