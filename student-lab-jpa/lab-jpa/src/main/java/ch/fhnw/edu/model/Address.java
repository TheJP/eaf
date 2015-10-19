package ch.fhnw.edu.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {

	protected Address() {
	}

	public Address(String street, String city) {
		this.street = street;
		this.city = city;
	}

	@Id
	private int id;

	private String street;
	private String city;

	public int getId() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
