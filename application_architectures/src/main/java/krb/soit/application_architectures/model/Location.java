package krb.soit.application_architectures.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AA_LOCATIONS")
public class Location {
	@Id
	@Column(name = "LOCID")
	private int id;
	
	@Column(name = "LOCNAME")
	private String name;
/*============================================*/
	public Location() {
	}
	public Location(int id, String name) {
		this.id = id;
		this.name = name;
	}
/*============================================*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
