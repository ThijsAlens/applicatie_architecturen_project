package klusjes_v2.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "people")
public class People {

    @Id
    @NotBlank
    @Column(name = "USERNAME")
    private String username;
    
    @NotBlank
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "VOORNAAM")
    private String voornaam;
    
    @Column(name = "ACHTERNAAM")
    private String achternaam;

    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

	public People(@NotBlank String username, @NotBlank String password, String voornaam, String achternaam) {
		super();
		this.username = username;
		this.password = password;
		this.voornaam = voornaam;
		this.achternaam = achternaam;
	}

	public People() {
		super();
	}
    
    
    
    
}
