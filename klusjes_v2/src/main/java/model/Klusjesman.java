package model;

import java.util.ArrayList;

public class Klusjesman{
	private String username;
	private String password;
	private float rating;
	
	public ArrayList<Klusje> getToegewezenKlusjes() {
		//haal de klusjes op uit de DB waarbij this de toegewezenKlusjesman is
		return new ArrayList<Klusje>();
	}
	
	public ArrayList<Klusje> getNietToegewezenKlusjes() {
		//haal de klusjes op uit de DB waarbij de status 'BESCHIKBAAR' of 'GEBODEN' is
		return new ArrayList<Klusje>();
	}
}
