package model;

import java.util.ArrayList;

public class Klant {
	private String username;
	private String password;
	
	public ArrayList<Klusje> getKlusjes() {
		//haal de klusjes op uit de DB waarbij this de klant is
		return new ArrayList<Klusje>();
	}
}
