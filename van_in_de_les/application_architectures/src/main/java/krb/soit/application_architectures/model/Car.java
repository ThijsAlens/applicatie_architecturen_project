package krb.soit.application_architectures.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AA_cars")
public class Car {
	@Id
	@Column(name = "CARID")
	private int id;
	
	@Column(name = "CARTYPE")
	private String type;
	
	@Column(name = "CARPRICE")
	private double price;
/*============================================*/
	public Car() {
	}
	
	public Car(int id, String type, double price) {
		this.id = id;
		this.type = type;
		this.price = price;
	}
/*============================================*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
