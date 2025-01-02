package klusjes_v2.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "klant")
public class Klant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    @Column(name = "KLANT_ID")
    private Integer klantId;

    @OneToOne
    @JoinColumn(name = "PEOPLE_ID", referencedColumnName = "USERNAME")
    private People people;  

    public Integer getKlantId() {
        return klantId;
    }

    public void setKlantId(Integer klantId) {
        this.klantId = klantId;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

	public Klant(@NotBlank Integer klantId, People people) {
		this.klantId = klantId;
		this.people = people;
	}
    
    public String getUsername() {
    	// return username
    	return "";
    }
    
    public Klant() {
    	
    }

	public Klant(People people) {
		super();
		this.people = people;
	}

	
	
    
    
}
