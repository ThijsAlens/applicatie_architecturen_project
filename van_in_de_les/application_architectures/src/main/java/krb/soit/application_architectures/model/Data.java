package krb.soit.application_architectures.model;

import java.util.ArrayList;

public class Data {
	private ArrayList<Car> cars;
	private ArrayList<Location> locations;
	
/*============================================*/
	
	public Data() {
		this.cars = new ArrayList<Car>();
		this.locations = new ArrayList<Location>();
	}
	
/*============================================*/
	
	public ArrayList<Car> getCars() {
		return cars;
	}
	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}
	public ArrayList<Location> getLocations() {
		return locations;
	}
	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}
	
	
}
