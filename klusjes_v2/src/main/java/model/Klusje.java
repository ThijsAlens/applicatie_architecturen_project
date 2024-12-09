package model;

import java.util.ArrayList;

public class Klusje {
	private Klant klant;
	private Status_enum status;
	private String beschrijving;
	private float prijs;
	private ArrayList <Klusjesman> gebodenKlusjesmannen;
	private Klusjesman toegewezenKlusjesman;
	private float score;
	
	public enum Status_enum {
        BESCHIKBAAR,
        GEBODEN,
        TOEGEWEZEN,
        UITGEVOERD,
        BEOORDEELD
    }

	public Klant getKlant() {
		return klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

	public Status_enum getStatus() {
		return status;
	}

	public void setStatus(Status_enum status) {
		this.status = status;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public float getPrijs() {
		return prijs;
	}

	public void setPrijs(float prijs) {
		this.prijs = prijs;
	}

	public ArrayList<Klusjesman> getGebodenKlusjesmannen() {
		return gebodenKlusjesmannen;
	}

	public void setGebodenKlusjesmannen(ArrayList<Klusjesman> gebodenKlusjesmannen) {
		this.gebodenKlusjesmannen = gebodenKlusjesmannen;
	}

	public Klusjesman getToegewezenKlusjesman() {
		return toegewezenKlusjesman;
	}

	public void setToegewezenKlusjesman(Klusjesman toegewezenKlusjesman) {
		this.toegewezenKlusjesman = toegewezenKlusjesman;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	
//========================================================================================================================//
	
	public void addKlusjesman(Klusjesman k) {
		this.gebodenKlusjesmannen.add(k);
	}
	
}
