package klusjes_v2.model;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AA_klusjes")
public class Klusje {
	@Id
	@Column(name = "KLUSJESID")
	private int id;
	
	@Column(name = "KLANT")
	private Klant klant;
	
	@Column(name = "STATUS")
	private Status_enum status;
	
	@Column(name = "BESCHRIJVING")
	private String beschrijving;
	
	@Column(name = "PRIJS")
	private double prijs;
	
	@Column(name = "GEBODEN_KLUSJESMANNEN")
	private ArrayList <Klusjesman> gebodenKlusjesmannen;
	
	@Column(name = "TOEGEWEZEN_KLUSJESMAN")
	private Klusjesman toegewezenKlusjesman;
	
	@Column(name = "SCORE")
	private float score;
	
	public static enum Status_enum {
        BESCHIKBAAR,
        GEBODEN,
        TOEGEWEZEN,
        UITGEVOERD,
        BEOORDEELD
    }
	
//*========================================================================*//
	
	public Klusje() {
	}
	
	public Klusje(int id, Klant klant, String beschrijving, double prijs) {
		this.id = id;
		this.klant = klant;
		this.status = Status_enum.BESCHIKBAAR;
		this.beschrijving = beschrijving;
		this.prijs = prijs;
		this.gebodenKlusjesmannen = new ArrayList<Klusjesman>();
		this.toegewezenKlusjesman = null;
		this.score = -1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
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
	
//*========================================================================*//
	
	public void addKlusjesman(Klusjesman k) {
		this.gebodenKlusjesmannen.add(k);
	}
	
}
